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
		sql="select * from board order by root desc,step asc";
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
		sql = "select * from board where seq = ?";
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
				dto.setRoot(rs.getInt(7));
				dto.setStep(rs.getInt(8));
				dto.setIndent(rs.getInt(9));
				System.out.println(dto);
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
	
	// ** insert : 원글입력
	   // => 입력 컬럼: id, title, content 
	   //    default값: regdate, cnt, step, indent
	   // => root : seq 와 동일한 값   
	   // => Auto_Inc: seq (계산: auto 보다 IFNULL(max(seq)...) 를 적용)
	public int boardinsert(BoardDTO dto) {
		sql="insert into board values(\r\n"
				+ "	(select* from (select ifNull(max(seq),0)+1 from board) as temp),\r\n"
				+ "	?,?,?, Current_TimeStamp, 0, (select* from (select ifNull(max(seq),0)+1 from board) as temp), 0, 0\r\n"
				+ "	);";
		
		try {
			pst = cn.prepareStatement(sql);

			pst.setString(1,dto.getId());
			pst.setString(2,dto.getTitle());
			pst.setString(3,dto.getContent());
			return pst.executeUpdate();
		} catch (Exception e) {
			System.out.println(" ** BOARD Insert Exception => " + e.toString());
			return 0;
		}
	}
	
	   // ** replyInsert : 답글입력
	   // => seq: IFNULL 이용
	   // => 입력 컬럼: id, title, content, root, step, indent
	   // => JDBC subQuery 구문 적용시 주의사항
	   //     -> MySql: select 구문으로 한번더 씌워 주어야함 (insert 의 경우에도 동일)   
	   // => stepUpdate 가 필요함
	   //    댓글 입력 성공후 실행
	   //     -> 현재 입력된 답글의 step 값은 수정되지 않도록 sql 구문의 조건 주의    
		// => boardList 의 출력 순서 확인
		// 
	public int rinsert(BoardDTO dto) {
		sql = "insert into board(seq,id,title,content,root,step,indent) values("
                + " (select * from (select ifNull(max(seq),0)+1 from board) as temp), " //seq(최종 게시글을 기준으로)를 증가시키는 것. 
                + " ?, ?, ?, ?, ?, ?) ";

		try {
			pst = cn.prepareStatement(sql);

			pst.setString(1,dto.getId());
			pst.setString(2,dto.getTitle());
			pst.setString(3,dto.getContent());
			pst.setInt(4,dto.getRoot() );
			pst.setInt(5, dto.getStep());
			pst.setInt(6, dto.getIndent());
			
			
			pst.executeUpdate();// 답글 등록 성공 -> step update
			System.out.println("** step Update Count =>" + stepUpdate(dto) );
			return 1;
		} catch (Exception e) {
			System.out.println(" ** BOARD Insert Exception => " + e.toString());
			return 0;
		}
		
	}
	// ** stepUpdate 증가 
	
	// => 조건
	// root 동일 and step 값은 >= and 새 글은 제외 
	//step = 순위 root =원글의 번호
	// (select* from (select ifNull(max(seq),0) from board) as temp) : dto 값 내부에는 seq 값이 없기 때문에 
	public int stepUpdate(BoardDTO dto) {
		sql = "update board set step=step+1 where root=? and step>=? "
                //현재값을 찾기 위해서 서브쿼리 사용하기
                + "and seq <> (select * from (select ifNull(max(seq),0) from board) as temp) ";
		try {						// 내가 방금 단 답글을 제외하고, 나머지 전의 답을의 step의 순서를 1씩 올림. 
			pst = cn.prepareStatement(sql);
			pst.setInt(1, dto.getRoot() );
			pst.setInt(2, dto.getStep() );
			System.out.println(pst);
			
			return pst.executeUpdate(); //수정된 data 갯수를 리턴
			
		} catch (Exception e) {
			System.out.println("** step Update =>" + e.toString() );
			return 0;
			
		}
		
	}
	
	
	
	//update
	public int update(BoardDTO dto) {
		sql="update board SET title = ?, content = ?, regdate= ?, cnt =? where seq = ?";
		try {
			pst = cn.prepareStatement(sql);
			
			pst.setString(1, dto.getTitle() );
			pst.setString(2, dto.getContent() );
			pst.setString(3, dto.getRegdate() );
			pst.setInt(4, dto.getCnt() );
			
			pst.setInt(5, dto.getSeq() );
			
			return pst.executeUpdate();
		} catch (Exception e) {
			System.out.println(" ** BOARD update Exception => " + e.toString());
			return 0;
		}
		
	}
	
	//** delete
	// => seq 로 삭제
	// => 답글 추가 후 : 원글과 답글 구분
	// 		-> 원글 : 루트가 동일한 친구 where root=? 
	//		-> 답글 : 답글만 삭제 where seq = ?
//	public int delete(int seq) {
	public int delete(BoardDTO dto) {
		// 원글 삭제
		if(dto.getSeq()==dto.getRoot()) {
			sql = "delete from board where root = ? " ;
		}else {
			sql = "delete from board where seq = ? " ;
		}
		try {
			pst= cn.prepareStatement(sql);
			pst.setInt(1, dto.getSeq());		
			
			return pst.executeUpdate();
		} catch (Exception e) {
			System.out.println(" ** delete Exception => ** " + e.toString());
			return 0;

		}
	} 
	
}
