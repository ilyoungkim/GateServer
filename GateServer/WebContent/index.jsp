<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.io.*, java.util.*" %>
<%@page import="java.sql.Date"%>
<%@ page import="net.coinswallet.gateway.database.*, net.coinswallet.gateway.command.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<% 
// CONDITION TYPE SETTING
boolean DEBUG_TOGGLE = true;

%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Gateway server page</title>
</head>
<body>
<p>
<%
	//0. queryString check
	if (request.getQueryString() == null) {
		// 99. handling error a queryString 
		session.invalidate();
		out.println("E9999:Query String message error");
	} else {
		//0. queryString check
		if (request.getQueryString().length() > 1) {
			// 1. jsession management
			String id = session.getId();
			long cTime = session.getCreationTime();
			long lTime = session.getLastAccessedTime();
			if (DEBUG_TOGGLE)
				System.out.println(id);					
			
			// 2. save from request parameters to Properties object 	
			Enumeration<String> params = request.getParameterNames();
			Properties props = new Properties();
			while (params.hasMoreElements()) {
				String key = (String) params.nextElement();
				String value[] = request.getParameterValues(key);
				props.setProperty(key, value[0]);
			}
			if (DEBUG_TOGGLE)
				System.out.println(props);
			// 2.1 store the jseesion information
			MariaDAO dao = new MariaDAO();
			java.sql.Date date = new java.sql.Date(cTime);
			if (!dao.existSession(id, date)) { 
				dao.saveSession(id, date, props.getProperty("cmd"), props.getProperty("good.id"));
			}

			// 3. Analysis cmd 
			if (props.containsKey("cmd")) {
				String command = props.getProperty("cmd");
				// 3.1. normal connection
				if ("normal.connect".equals(command)) {

				} else if ("test.connet".equals(command)) {

				} else if ("price.bitcoin".equals(command)) {

				} else if ("price.ether".equals(command)) {

				} // 3.5. connect a initial product 
				else if ("good.init".equals(command)) {

				} else if ("good.connnet".equals(command)) {

				} else if ("good.regist".equals(command)) {

				} else if ("good.lost".equals(command)) {

				} else if ("good.find".equals(command)) {

				} else if ("asset.send".equals(command)) {

				} else if ("asset.receive".equals(command)) {

				} else if ("asset.log".equals(command)) {

				} else if ("mnemonic.regist".equals(command)) {

				} else if ("mnemonic.restore".equals(command)) {

				}
				else {
					// 99. handling error a cmd message
					session.invalidate();
					out.println("E8889:Command abnormal message");
					dao.removeSession(id, date);
				}
			} else {
				// 99. handling error a cmd message
				session.invalidate();
				out.println("E8888:Command message error");
				dao.removeSession(id, date);
			}
		} else {
			// 99. handling error a queryString
			session.invalidate();
			out.println("E9999:Query String message error");
		}
		
	}
%>
</p>
</body>
</html>