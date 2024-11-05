package com.myaws.myapp.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

// ȸ�������� ���ǿ� ��� ����
// HandlerInterceptorAdapter
public class LoginInterceptor extends HandlerInterceptorAdapter {

	// �α��� �Ŀ� ȸ�������� ���ǿ� ��´�.
	
	@Override // �����ϱ� ����
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		// ����ä�� �ϱ� ���� ó���ϴ� �޼ҵ�
		// ���� ������� ������ �ִ°� �ִٸ� ����� ���
		
		HttpSession session = request.getSession();
		
		if(session.getAttribute("midx")!= null) {
			session.removeAttribute("midx");
			session.removeAttribute("memberId");
			session.removeAttribute("memberName");
			session.invalidate();
		}

		return true;
	}

	/**
	 * This implementation is empty.
	 */
	@Override
	public void postHandle( // ������ ���ǿ� ��� ����
			HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
			throws Exception {
		
		// RedirectAttibutes이나 Model 사용한다.
		//ModelAndView 
		String midx = modelAndView.getModel().get("midx").toString();
		String memberId = modelAndView.getModel().get("memberId").toString();
		String memberName = modelAndView.getModel().get("memberName").toString();
		
		modelAndView.getModel().clear(); // �Ѿ���� �Ķ���� model���� �����.
		
		HttpSession session = request.getSession();
		if(midx!=null) {
			session.setAttribute("midx", midx);
			session.setAttribute("memberId", memberId);
			session.setAttribute("memberName", memberName);
		}
	}

	// 
}
