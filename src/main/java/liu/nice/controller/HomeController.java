package liu.nice.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import liu.nice.common.RandomValidateCode;
import liu.nice.pojo.User;
import liu.nice.service.UserService;


@Controller
@RequestMapping("/home")
public class HomeController {
	@Autowired
	UserService userservice;
	
	private static Logger log  = LogManager.getLogger(HomeController.class);
	@RequestMapping("/login")
	public String login()
	{
		return "page/login";
	}

	@RequestMapping(value="/login.do",produces = {"text/html;charset=utf-8"})
	public String login(@RequestParam("username") String username,@RequestParam String password,@RequestParam String verifycode,HttpSession session,Model model)
	{
		//����֤��֤��
		log.debug(verifycode+"----------------------verycode---------------------");
		//��session�л�ȡ�����
        String random = (String) session.getAttribute("RANDOMVALIDATECODEKEY");
		log.debug(random+"----------------------random---------------------");

        if(!random.equals(verifycode))
        {
        	model.addAttribute("error","��֤�����");
        	return "page/login";
        }

		User user = userservice.login(username, password);
		if(user==null)
		{
			return "page/login";
		}else {
			session.setAttribute("user", user);
			return "redirect:/admin/index";
		}
	}
	
	@RequestMapping(value="/loginout",produces = {"text/html;charset=utf-8"})
	public String loginOut(HttpSession session) {
		session.removeAttribute("user");
		return "redirect:login";
	}
	
	
	/**
     * ��¼ҳ��������֤��
     */
    @RequestMapping(value = "/getverify")
    public void getVerify(HttpServletRequest request, HttpServletResponse response){
        response.setContentType("image/jpeg");//������Ӧ����,������������������ΪͼƬ
        response.setHeader("Pragma", "No-cache");//������Ӧͷ��Ϣ�������������Ҫ���������
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expire", 0);
        RandomValidateCode randomValidateCode = new RandomValidateCode();
        try {
            randomValidateCode.getRandcode(request, response);//�����֤��ͼƬ����
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
