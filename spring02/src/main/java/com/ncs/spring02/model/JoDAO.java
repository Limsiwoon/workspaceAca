package com.ncs.spring02.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ncs.spring02.domain.JoDTO;

@Repository
public class JoDAO {
	private static Connection conn = DBConnection.getConnection();
	private static PreparedStatement pst;
	private static ResultSet rs;
	private static String sql;
	
	// 1 ) joSelectList
	public List<JoDTO> joSelectList(){
		sql = "select * from jo";
		
		List<JoDTO> list = new ArrayList<JoDTO>();
		
		try {
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			
			if(rs.next()) {
				do {
					JoDTO jdto = new JoDTO();
					jdto.setJno(rs.getInt(1));
					jdto.setJname(rs.getString(2));
					jdto.setCaptain(rs.getString(3));
					jdto.setProject(rs.getString(4));
					jdto.setSlogan(rs.getString(5));
					
					list.add(jdto);
				} while (rs.next());
				return list;
			
			} else {
				return null;
			}
			
		} catch (Exception e) {
			System.out.println(" ** SelectList Exception => " + e.toString());
			return null;
		}	
	}
	
	
	// 2 ) joSelectOne
	public JoDTO joSelectOne(int jno) {
		sql = "select * from jo where jno = ?";
		
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, jno);
			rs = pst.executeQuery();
			
			if(rs.next()) {
				JoDTO jdto = new JoDTO();
				jdto.setJno(rs.getInt(1));
				jdto.setJname(rs.getString(2));
				jdto.setCaptain(rs.getString(3));
				jdto.setProject(rs.getString(4));
				jdto.setSlogan(rs.getString(5));

				return jdto;
			} else {
				System.out.println(" 출력할 데이타가 전혀 없음 ");
				return null;
			}
		} catch (Exception e) {
			System.out.println(" ** selectOne Exception => " + e.toString());
			return null;
		}
	}
		
	// 3 ) joUpdate
		public int joupdate(JoDTO jdto){
			sql = "update jo SET jname =? , captain = ?, project = ?, slogan = ?"
				 + "where jno = ? ";
			
			try {
				pst = conn.prepareStatement(sql);

				pst.setString(1, jdto.getJname());
				pst.setString(2, jdto.getCaptain() );
				pst.setString(3, jdto.getProject());
				pst.setString(4, jdto.getSlogan());
				pst.setInt(5, jdto.getJno());
				
				return pst.executeUpdate();
			} catch (Exception e) {
				System.out.println(" ** JOupdate Exception => " + e.toString());
				return 0;
			}
		}

	// 4 ) jojoin
	public int joinsert(JoDTO jdto) {
		sql = "insert into jo values (?,?,?,?,?)";
		
		try {
			pst = conn.prepareStatement(sql);

			pst.setInt(1, jdto.getJno());
			pst.setString(2, jdto.getJname());
			pst.setString(3, jdto.getCaptain() );
			pst.setString(4, jdto.getProject());
			pst.setString(5, jdto.getSlogan());
			
			return pst.executeUpdate();
		} catch (Exception e) {
			System.out.println(" ** JOinsert Exception => " + e.toString());
			return 0;
		}
	}
	
	// 5 ) joDelete
	public int joDelete(int jno) {
		sql = "delete from jo where jno = ?";
		
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, jno);			
			
			return pst.executeUpdate();
		} catch (Exception e) {
			System.out.println(" ** jodelete Exception => ** " + e.toString());
			return 0;

		}
	}
}
