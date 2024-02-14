package pageTest;

import lombok.Getter;
import lombok.ToString;

//** PageMaker : View 에 필요한 값을 완성
//=> 전체Row 갯수 (전체 Page수 계산위해 필요)
//=> view 에 표시할 첫 PageNo
//=> view 에 표시할 끝 PageNo
//=> 1Page당 표시할 pageNo갯수
//=> 출력 가능한 마지막 PageNo (totalRowsCount, rowsPerPage 로 계산)
//=> 이전 PageBlock 으로
//=> 다음 PageBlock 으로

//=> Criteria 를 이용해서..

@Getter
@ToString
public class PageMaker {
	
	private int totalRowsCount; // 출력 대상 전체 Row갯수 : from DB
	private int displayPageNo=3; // 1 Page 당 표시할 PageNo 갯수 
	private int spageNo; // view에 표시할 첫 PageNo : 계산
	private int epageNo; // view에 표시할 끝 PageNo : 계산
	//column명 => 두번째 글자가 대문자인 경우 setter getter 할 때, 명확하지 않아서, sPageNo 는 사용 X 
	// ※ 주의 필드명이 ePage 처럼 두번째 알파벳이 대문자인 경우
	//    => setter, getter 는 setsPageNo , getsPageNo 형태로 만들어지기때문에
	//       Lombok.. 등등 과 규칙이다르므로 사용시 불편 
	//         -> 그러므로 대.소문자 섞어사용시 주의. 
	private int lastPageNo; // 츨력 가능한 마지막 pageNo: 계산
	
	private boolean prev;
	private boolean next;
	
	Criteria cri;
	
	// 필요값 계산
	// 1) Critertia
	public void setCri(Criteria cri) {
		this.cri=cri;
	}
	
	// 2) totalRowsCount
	// => 전체 Rows 갯수 : Read from DB
	// => 이 값을 이용해서, 나머지 필요한 값들을 계산 
	public void setTotalRowsCount(int totalRowsCount) {
		this.totalRowsCount=totalRowsCount;
		calcData();
	}
	
	// 3) 나머지 필요한 값들을 계산 => calcData()
	public void calcData() {
		
	    // 3.1) spageNo, epageNo
	    // => currPage가 속한 PageBlock 의 spageNo, epageNo 를 계산 
	      
	    // => pageNo 를 n 개씩출력 한다고 가정하면, epageNo는 항상 =3n
		//		3개씩 123 456 789 101112 131415 
	    //      displayPageNo=3 이면 3, 6, 9, 12,......
	    // => ex) 17 page 요청 , displayPageNo=3, 일때 17이 몇번째 그룹 인지 계산하려면,
	    //        17/3 -> 5 나머지 2 결론은 정수 나누기 후 나머지가 있으면 +1 이 필요함
	    //    -> Math.ceil(17/3) -> Math.ceil(5.73..) -> 6.0 -> 6번쨰 그룹 16,17,18
	    // 즉, 17이 몇번째 그룹 인지 계산하고, 여기에 * displayPageNo 를 하면 됨.   
	      
	    // ** Math.ceil(c) : 매개변수 c 보다 크면서 같은 가장 작은 정수를 double 형태로 반환 
	    //                   ceil -> 천장, 예) 11/3=3.666..  -> 4
	    // => Math.ceil(12.345) => 13.0   
		
		this.epageNo=(int)Math.ceil(cri.getCurrPage()/(double)displayPageNo )* displayPageNo;
		this.spageNo=(this.epageNo - displayPageNo)+1;
		// => 요청받은 currPage11 인 경우 -> (int)Math.ceil(11/3) * 3 = 12
		// => spageNo= 12-3+1=10 -> 결론은 11은 10, 11, 12 그룹에 속함. 
		
		// 3.2 ) lastPageNo 계산 & epageNo의 적합성
		this.lastPageNo=(int)Math.ceil(this.totalRowsCount/(double)cri.getRowsPerPage()); 
		if(this.epageNo > this.lastPageNo ) this.epageNo = this.lastPageNo;
		
		// 3.3) prev, next
		prev = this.spageNo==1 ? false : true ;
		next = this.epageNo==this.lastPageNo ? false : true ;
		// 페이지가 1 이거나 마지막 페이지면, 앞이나 뒤로 움직일 수 없음. 
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
