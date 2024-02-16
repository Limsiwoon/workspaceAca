package pageTest;

import lombok.Data;

@Data
public class SearchCriteria extends Criteria{
	//상속을 받아옴 => dto를 그때마다 받아올 수 없기 때문에, 상속을 받아옴. 
	
	private String searchType = "all"; // 컬럼을 선택하는 것. 
	private String keyword;
	
	private String[] check; // 여러 개가 체크되어 들어오는 경우, 배열사용
}
