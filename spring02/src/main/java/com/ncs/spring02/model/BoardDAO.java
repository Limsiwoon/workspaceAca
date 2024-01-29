package com.ncs.spring02.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ncs.spring02.domain.BoardDTO;

// ** 게시판
//	=> CRUD를 구현
@Repository
public class BoardDAO {
	
	private static Connection cn= DBConnection.getConnection();
	private static PreparedStatement pst;
	private static ResultSet rs;
	private static String sql;
	
	//**selectList
	public List<BoardDTO> selectList(){
		sql="select * from board order by seq desc";
		List<BoardDTO> list = new ArrayList<BoardDTO>();
		
		try {
			pst= cn.prepareStatement(sql);
			rs=pst.executeQuery();
			
			if(rs.next() ) {
				
				do {
					BoardDTO dto = new BoardDTO();
					dto.setSeq(rs.getInt(1));
					dto.setId(rs.getString(2));
					dto.setTitle(rs.getString(3));
					dto.setContent(rs.getString(4));
					dto.setRegdate(rs.getString(5));
					dto.setCnt(rs.getInt(6));
					dto.setRoot(rs.getInt(7));
					dto.setStep(rs.getInt(8));
					dto.setIndent(rs.getInt(9));
					
					list.add(dto);
					
				} while(rs.next() );
				return list;
				
			}else {
				System.out.println("Board 출력 자료 X");
				return null;
			}
		} catch (Exception e) {
			System.out.println(" **Board selectList => "+e.toString() );
			return null;
		}
	}
	
	//selectOne
	public BoardDTO selectOne(int seq) {
		sql = "select seq,id,title,content,regdate,cnt from board where seq = ?";
		try {
			pst= cn.prepareStatement(sql);
			pst.setInt(1, seq);
			rs=pst.executeQuery();
			
			if (rs.next() ) {	
				
				BoardDTO dto = new BoardDTO();
				dto.setSeq(rs.getInt(1));
				dto.setId(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setRegdate(rs.getString(5));
				dto.setCnt(rs.getInt(6));
						
				return dto;
			}else {
				System.out.println("솔직히 이거 필요 없지 않냐구..");
				return null;
			}
		} catch (Exception e) {
			System.out.println(" **Board selectOne 에러 => "+e.toString() );
			return null;
		}
	}
	
	public int insert(BoardDTO dto) {
		return 0;
	}
	
	public int update(BoardDTO dto) {
		return 0;
	}
	
	public int delete(int seq) {
		return 0;
	}
	
}
