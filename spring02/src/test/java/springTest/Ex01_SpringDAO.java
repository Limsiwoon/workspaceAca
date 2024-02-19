package springTest;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ncs.spring02.domain.MemberDTO;
import com.ncs.spring02.model.MemberDAO;

//** DAOTest Spring Version
//=> 설정화일(~.xml) 을  사용
// -> 테스트코드 실행시에 설정파일을 이용해서 스프링이 로딩 되도록 해줌
// -> @RunWith(스프링 로딩) , @ContextConfiguration (설정파일 등록)

//=> IOC/DI Test
//=> 공통적으로 사용하는 MemberDAO dao 인스턴스를 전역으로 정의
//=> 자동 주입 받기 ( xml_root-context.xml , @ )

//** SpringJUnit4ClassRunner.class 자동 import 안되면 직접 복.붙 해본다.  

//** import 제대로 안되고 오류발생시 Alt+f5 눌러 Maven Update 한다.
//=> 메뉴 : Project 우클릭 - Maven - Update Project .. 
// ( 하기전 주의사항은 pom.xml 의  <plugin> <configuration> 의 
//          <source>1.8</source> 와 <target> Java 버전 확인 )


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
//@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/**/*-context.xml")
//=> 단, servlet~~~.xml 에 @ scan 하도록 설정되어 있으므로 dao 가 이중으로 생성되어 오류발생
//<context:component-scan base-package="com.ncs.green, service, model" />
//=> "file:~~" 지정시 공백 없어야함
public class Ex01_SpringDAO {
	
	//** 자동주입
	//	=> 생성 : root-context.xml
	@Autowired
	MemberDAO dao;
	@Autowired
	MemberDTO dto;
	
	//Detail
	@Test
	public void detailTest() {
		// => 자동주입 확인
		
		System.out.println("** DAO 주입 확인 => " + dao);
		System.out.println("** DTO 주입 확인 => "+ dto);
		System.out.println("~~~~~~DAO 주입 DTO 주입 완료 ~~~~");
		System.out.println();
		
		
		String id="banana"; 
		//banana=true, black=false
		//black이 데이터가 없기 때문에, redline을 표시하고, 
		//banana는 데이터가 있기 때문에, greenline으로 표시 
		dto=dao.selectOne(id);
		assertNotNull(dto);
		
		
		System.out.println("~~~~~~ID 주입 확인~~~~");
		System.out.println("** DTO 주입 확인 => "+ dto);
		System.out.println("~~~~~~ID 주입 완료~~~~");
	}
	
	//insert
	
	//@Test
	public void insertTest() {
		dto.setId("junit");
		dto.setPassword("12345!");
		dto.setName("junit");
		dto.setAge(20);
		dto.setJno(7);
		dto.setInfo("JUnit Test");
		dto.setPoint(500.0);
		dto.setBirthday("2000-01-01");
		dto.setRid("lsw1");
		

	}
	
}
