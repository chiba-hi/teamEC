package jp.co.internous.framepj.controller;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.internous.framepj.model.domain.MstCategory;
import jp.co.internous.framepj.model.domain.MstProduct;
import jp.co.internous.framepj.model.form.SearchForm;
import jp.co.internous.framepj.model.mapper.MstCategoryMapper;
import jp.co.internous.framepj.model.mapper.MstProductMapper;
import jp.co.internous.framepj.model.session.LoginSession;

/**
 * 商品検索に関する処理を行うコントローラー
 * @author インターノウス
 *
 */
@Controller
@RequestMapping("/frameweb")
public class IndexController {
	
	@Autowired
	private MstCategoryMapper categoryMapper;
	
	@Autowired
	private MstProductMapper productMapper;
	
	@Autowired
	private LoginSession loginSession;
	
	/**
	 * トップページを初期表示する。
	 * @param m 画面表示用オブジェクト
	 * @return トップページ
	 */
	@RequestMapping("/")
	public String index(Model m) {
		
		if (loginSession.isLoggedIn() == false && loginSession.getTmpUserId() == 0) {
			
			int randomNum = new Random().nextInt(900_000_000) + 100_000_000;
			randomNum = -randomNum;
			loginSession.setTmpUserId(randomNum);
		}
		
		List<MstProduct> products = productMapper.find();
		m.addAttribute("products", products);
		
		List<MstCategory> categories = categoryMapper.find();
		m.addAttribute("categories", categories);
		
		m.addAttribute("loginSession",loginSession);
		
		return "index";
	}
	
	/**
	 * 検索処理を行う
	 * @param f 検索用フォーム
	 * @param m 画面表示用オブジェクト
	 * @return トップページ
	 */
	@RequestMapping("/searchItem")
	public String searchItem(SearchForm f, Model m) {
		
		String keywords = f.getKeywords();
		int category = f.getCategory();
		String editedKeywords = keywords.replace("　"," ").replaceAll(" +"," ").trim();
		String[] keywordArry = editedKeywords.split(" ");
		
		if (category == 0) {
			List<MstProduct> products = productMapper.findByProductName(keywordArry);
			m.addAttribute("products", products);
		} else {
			List<MstProduct> products = productMapper.findByCategoryAndProductName(category, keywordArry);
			m.addAttribute("products", products);
		}
		
		m.addAttribute("keywords", editedKeywords);
		
		List<MstCategory> categories = categoryMapper.find();
		m.addAttribute("categories", categories);
		m.addAttribute("selected", category);
		m.addAttribute("loginSession",loginSession);
		
		return "index";
	}
}
