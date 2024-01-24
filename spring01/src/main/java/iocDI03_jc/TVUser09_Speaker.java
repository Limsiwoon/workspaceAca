package iocDI03_jc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.AbstractRefreshableApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Component;

//** Java Bean Configuration class를 이용한 DI
//=> Bean 컨테이너 : AnnotationConfigApplicationContext 사용
//=> Test02 : 스피커 1개 사용 TV

//@Component
class Speaker {
	public Speaker() {
		System.out.println("~~ Speaker Default 생성자 ~~");
	}

	public void volumeUp() {
		System.out.println("~~ Speaker volumeUp ~~");
	}

	public void volumeDown() {
		System.out.println("~~ Speaker volumeDown ~~");
	}
} // Speaker

// 1) 고전적 방법 (직접 new : 소스 재컴파일)
// => Speaker 생성 : 직접코드에서
//@Component("sstv")
class SsTVs implements TV {

	private Speaker speaker = new Speaker();

	public SsTVs() {
		System.out.println("~~ SsTVs Default 생성자 ~~");
	}

	@Override
	public void powerOn() {
		System.out.println("~~ SsTVs powerOn ~~");
	}

	@Override
	public void powerOff() {
		System.out.println("~~ SsTVs powerOff ~~");
	}

	@Override
	public void volumeDown() {
		speaker.volumeDown();
	}

	@Override
	public void volumeUp() {
		speaker.volumeUp();
	}
} // SsTVs

//2) IOC/DI
//=> Speaker 전달 : 맴버변수를 통해 직접 전달 
//=> @Autowired
// - 자동주입, 맴버변수에 적용,  
//- Only 전달만 : 생성문 "~ = new Speaker()" 에서 "=" 역할만
//  그러므로 해당 객체는 반드시 생성 되어있어야 함.   
//=> 맴버별(개별적으로)로 각각 지정해야함
//@Autowired
//private Speaker speaker ;
//@Autowired
//private Student student;

//=> Autowired 의 실행규칙 
// -> 메모리에서 타입이 일치하는 객체를 찾아 있으면 제공
// -> 없으면 Autowired 구문에서 오류 : UnsatisfiedDependencyException: Error creating bean wit....
// -> required = false ( default 값은 true -> 없으면 Exception 발생 )
//	  required = true 란 ? 
//     못찾으면 Exception 을 발생하지않고 null return 
//    그러므로 인스턴스 호출구문( volumeUp, volumeDown) 에서 NullPointExceptiopn 발생
//@Component("lgtv")
class LgTVs implements TV {


	private Speaker speaker;
	//자동주입 받는다는 의미 . 타입이 일치하면 가져옴. 
//	private Speaker speaker = new Speaker();
	private String color;
	private int price;

	public LgTVs() {
		System.out.println("~~ LgTVs Default 생성자 ~~");
	}

	// new LgTVs(speaker,"Green",12345000)
	public LgTVs(Speaker speaker, String color, int price) {
		this.speaker = speaker;
		this.color = color;
		this.price = price;
		System.out.printf("~~ LgTVs 초기화 생성자 : color=%s, price=%d \n", color, price);
	}

	@Override
	public void powerOn() {
		System.out.println("~~ LgTVs powerOn ~~");
	}

	@Override
	public void powerOff() {
		System.out.println("~~ LgTVs powerOff ~~");
	}

	@Override
	public void volumeDown() {
		speaker.volumeDown();
	}

	@Override
	public void volumeUp() {
		speaker.volumeUp();
	}
} // LgTVs

// 3) IOC/DI -> setter 주입
// => Speaker전달 :멤버 변수를 통해 직접 전달
// => @Autowired

//@Component("aitv")
class AiTVs implements TV {
	
	@Autowired
	private Speaker speaker;
	
	private String color;
	private int price;

	public AiTVs() {
		System.out.println("~~ AiTVs Default 생성자 ~~");
	}

	public void setSpeaker(Speaker speaker) {
		this.speaker = speaker;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public void powerOn() {
		System.out.printf("~~ AiTVs powerOn : color=%s, price=%d \n", color, price);
	}

	@Override
	public void powerOff() {
		System.out.println("~~ AiTVs powerOff ~~");
	}

	@Override
	public void volumeDown() {
		speaker.volumeDown();
	}

	@Override
	public void volumeUp() {
		speaker.volumeUp();
	}
} // AiTVs

public class TVUser09_Speaker {

	public static void main(String[] args) {
		// 1. 스프링 컨테이너 생성
		AbstractApplicationContext sc = 
				new AnnotationConfigApplicationContext(JavaConfig02.class);

		// 2. 필요한 객체를 전달받고 서비스 실행
		System.out.println("** 1) 고전적 방법 (직접 new : 소스 재컴파일) **");
		TV sstv = (TV) sc.getBean("sstv");
		sstv.powerOn();
		sstv.volumeDown();
		sstv.volumeUp();
		sstv.powerOff();

		System.out.println("** 2) IOC/DI -> 생성자 직접 주입 **");
		TV lgtv = (TV) sc.getBean("lgtv");
		lgtv.powerOn();
		lgtv.volumeDown();
		lgtv.volumeUp();
		lgtv.powerOff();

		System.out.println("** 3) IOC/DI -> JC 에서 xml 병행 사용 **");
		TV aitv = (TV) sc.getBean("aitv");
		aitv.powerOn();
		aitv.volumeDown();
		aitv.volumeUp();
		aitv.powerOff();

	}

}
