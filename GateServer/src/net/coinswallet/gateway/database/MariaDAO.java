package net.coinswallet.gateway.database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MariaDAO {

	/**
	 * Sql statement define.
	 */
	private final String saveSessionSql = "INSERT INTO cw_gateway.cw_session (session_id, session_ctime, command, good_id) VALUES(?,?,?,?)";
	private final String removeSessionSql = "DELETE FROM cw_gateway.cw_session WHERE session_id=? and session_ctime=?";

	/**
	 * Get a connection 
	 * @return java.sql.Connection
	 * @throws Exception
	 */
	private java.sql.Connection registerDriver() throws Exception {
		Class.forName("org.mariadb.jdbc.Driver");
		return DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/cw_gateway", "cwuser", "Coinswallet!1234");
	}

	
	public boolean existSession(String sessionId, Date ctime) {
		boolean exist = false;
		
		return exist;
	}

	/**
	 * Save a session
	 * @param sessionId
	 * @param ctime
	 * @param command
	 * @param goodId
	 */
	public void saveSession(String sessionId, Date ctime, String command, String goodId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = registerDriver();
			if (conn!=null) { System.out.println("Connection is success!");
			} else { System.err.println("Connection is fail!");
				throw new IllegalStateException("Connection is fail!");
			}
			
			pstmt = conn.prepareStatement(saveSessionSql);
			pstmt.setString(1, sessionId);
			pstmt.setDate(2, ctime);
			if (command!=null) {
				pstmt.setString(3, command);
			}
			else {
				pstmt.setString(3, "none");
			}
			if (goodId!=null) {
				pstmt.setString(4, goodId);
			}
			else {
				pstmt.setString(4, "none");
			}
			int result = pstmt.executeUpdate();
			if (result>0) {
				System.out.println("[Success] Save a session : " + sessionId);
			}
			else {
				System.out.println("[Failed] Nothing a session : " + sessionId);
			}
		}
		catch (Exception ex) {
			System.err.println("[Session] Error > " + ex.getMessage());
		}
		finally {
			if (conn!=null) {
				try {
					pstmt.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * remove a session
	 * @param sessionId
	 * @param ctime
	 */
	public void removeSession(String sessionId, Date ctime) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = registerDriver();
			if (conn!=null) { System.out.println("Connection is success!");
			} else { System.err.println("Connection is fail!");
				throw new IllegalStateException("Connection is fail!");
			}
			
			pstmt = conn.prepareStatement(removeSessionSql);
			pstmt.setString(1, sessionId);
			pstmt.setDate(2, ctime);

			int result = pstmt.executeUpdate();
			if (result>0) {
				System.out.println("[Success] Remove a session : " + sessionId);
			}
			else {
				System.out.println("[Failed] Nothing a session : " + sessionId);
			}
		}
		catch (Exception ex) {

		}
		finally {
			if (conn!=null) {
				try {
					pstmt.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

/////////////////////////////// 	
}
