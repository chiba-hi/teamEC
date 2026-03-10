package jp.co.internous.framepj.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import jp.co.internous.framepj.model.domain.TblCart;
import jp.co.internous.framepj.model.domain.dto.CartDto;
import jp.co.internous.framepj.model.form.CartForm;
import jp.co.internous.framepj.model.mapper.TblCartMapper;
import jp.co.internous.framepj.model.session.LoginSession;


/**
 * カート情報に関する処理のコントローラー
 * @author インターノウス
 *
 */
@Controller
@RequestMapping("/frameweb/cart")
public class CartController {
	
	@Autowired
	private TblCartMapper cartMapper;
	
	@Autowired
	private LoginSession loginSession;

	private Gson gson = new Gson();
	

	/**
	 * カート画面を初期表示する。
	 * @param m 画面表示用オブジェクト
	 * @return カート画面
	 */
	@RequestMapping("/")
	public String index(Model m) {
		
		int userId = (loginSession.isLoggedIn()) ? loginSession.getUserId() : loginSession.getTmpUserId();
		
		List<CartDto> carts = cartMapper.findByUserId(userId);
		m.addAttribute("carts",carts);
		
		m.addAttribute("loginSession",loginSession);
		
		return "cart";
	}

	/**
	 * カートに追加処理を行う
	 * @param f カート情報のForm
	 * @param m 画面表示用オブジェクト
	 * @return カート画面
	 */
	@RequestMapping("/add")
	public String addCart(CartForm f, Model m) {
		
		int userId = (loginSession.isLoggedIn()) ? loginSession.getUserId() : loginSession.getTmpUserId(); 
		
		int isAlreadyInCartCount = cartMapper.findCountByUserIdAndProductId(userId, f.getProductId());

		TblCart cart = new TblCart();
		cart.setUserId(userId);
		cart.setProductId(f.getProductId());
		cart.setProductCount(f.getProductCount());
		
		if(isAlreadyInCartCount > 0 ) { 
			cartMapper.update(cart);
		}else {
			cartMapper.insert(cart);
		}
		
		List<CartDto> carts = cartMapper.findByUserId(userId);
		m.addAttribute("carts",carts);
		
		m.addAttribute("loginSession",loginSession);
			
		return "cart";
	}

	/**
	 * カート情報を削除する
	 * @param checkedIdList 選択したカート情報のIDリスト
	 * @return true:削除成功、false:削除失敗
	 */
	@PostMapping("/delete")
	@ResponseBody
	public boolean deleteCart(@RequestBody String checkedIdList) {
		
		JsonObject jsonObject = gson.fromJson(checkedIdList, JsonObject.class);
		
		List<Integer> checkedIds = gson.fromJson(
			    jsonObject.get("checkedIdList"), 
			    new TypeToken<List<Integer>>(){}.getType()
			);
		
		int deletedCount = cartMapper.deleteById(checkedIds);
		
		return deletedCount > 0;
		
	}
}
