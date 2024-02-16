package pageTest;

import lombok.Getter;
import lombok.ToString;

//** Criteria : (판단이나 결정을 위한) 기준
//=> 출력할 Row 를 select 하기 위한 클래스
//=> 이것을 위한 기준 값들을 관리

//** PageMaker : UI에 필요한 정보 완성

//** Paging 을 하려면 ... **
//=> 1Page에 출력할 Row 갯수 :  5개
//=> 현재 출력(요청) Page
//=> 출력할 List (Rows) 
// -> start Row 순서번호 : 계산필요
//=> Criteria Class 에 정의 

//=> 1Page 출력 PageNo 갯수 : 10개
// -> PagreBlock 의 First Page No 
// -> PagreBlock 의 Last Page No
// -> 전진, 후진 표시여부
// -> go 전체의 First Page 표시여부
// -> go 전체의 Last Page 표시여부
//=> PageMaker Class 로 처리 

@Getter //setter는 필요 없기 때문에, @data를 활용하지 않음.
@ToString
public class Criteria {
	
	private int rowsPerPage; //1Page에 출력할 Row 갯수
	private int currPage; //현재 출력(요청) Page
	private int sno; //start Row 순서번호
	private int eno; // end row 순서번호 : 계산 필요 (Oracleaks 필요 => limit이 없기 떄문에 , rownum을 활용해야함.)
	
	// 1) 기본생성자 : 기본값 초기화 
	public Criteria() {
		this.rowsPerPage=5;
		this.currPage=1;
	}
	
	// 2) 요청시 값 갱신
	//2.1) currPage
	public void setCurrPage(int currPage) {
		if(currPage>1) this.currPage=currPage;
		else this.currPage=1;
	}
	
	// 2.2) rowsPerPage 
	// => 1페이지당 보여줄 Row(Record,튜플) 갯수 확인
	// => 제한조건 점검 ( 50개 까지만 허용)
	// => 당장은 사용하지 않지만 사용가능하도록 작성   
	public void setRowsPerPage(int rowsPerPage) {
		if(rowsPerPage>5 && rowsPerPage <51) this.rowsPerPage=rowsPerPage;
		else this.rowsPerPage=5;
	}
	
	//2.3) setSnoEno : sno, eno 계산
	//sno는 식으로 구해야함. 
	public void setSnoEno() {
		if(this.sno<1) this.sno=1; // 방어코드같은 것
		this.sno=(this.currPage-1)*this.rowsPerPage;
		// => MySql : limit 5,5 (인덱스 5에서 5개까지)
		// => Oracle : 6~10
		// this.sno =(this.currPage-1)*this.rowsPerPage+1;
		// this.eno =this.sno+this.rowsPerPage
	}
	 

	
	
}// class
