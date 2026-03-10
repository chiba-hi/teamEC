package jp.co.internous.framepj.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import jp.co.internous.framepj.model.domain.MstDestination;
import jp.co.internous.framepj.model.domain.MstUser;
import jp.co.internous.framepj.model.form.DestinationForm;
import jp.co.internous.framepj.model.mapper.MstDestinationMapper;
import jp.co.internous.framepj.model.mapper.MstUserMapper;
import jp.co.internous.framepj.model.session.LoginSession;

/**
 * 宛先情報に関する処理のコントローラー
 * @author インターノウス
 *
 */
@Controller
@RequestMapping("/frameweb/destination")
public class DestinationController {
	
	@Autowired
	private LoginSession loginSession;
	
	@Autowired
	private MstUserMapper userMapper;
	
	@Autowired
	private MstDestinationMapper destinationMapper;
	
	private Gson gson = new Gson();
	
	/**
	 * 宛先画面を初期表示する
	 * @param m 画面表示用オブジェクト
	 * @return 宛先画面
	 */
	@RequestMapping("/")
	public String index(Model m) {
		
		MstUser user = userMapper.findByUserNameAndPassword(loginSession.getUserName(), loginSession.getPassword());
		
		m.addAttribute("user",user);
		
		m.addAttribute("loginSession",loginSession);
		
		return "destination";
		
	}
	
	/**
	 * 宛先情報を削除する
	 * @param destinationId 宛先情報ID
	 * @return true:削除成功、false:削除失敗
	 */
	@SuppressWarnings("unchecked")
	@PostMapping("/delete")
	@ResponseBody
	public boolean delete(@RequestBody String destinationId) {
		
		Map<String,Object> map = gson.fromJson(destinationId, Map.class);
		
		int destinationIdInt = Integer.parseInt(map.get("destinationId").toString());
		
		return destinationMapper.logicalDeleteById(destinationIdInt) > 0;
	}
	
	/**
	 * 宛先情報を登録する
	 * @param f 宛先情報のフォーム
	 * @return 宛先情報id
	 */
	@PostMapping("/register")
	@ResponseBody
	public String register(@RequestBody DestinationForm f) {

		MstDestination destination = new MstDestination(f);
		destination.setUserId(loginSession.getUserId());
		
		destinationMapper.insert(destination);
		
		int newId = destination.getId();
		
		return String.valueOf(newId);
	
	}
}
