package iocDI02_anno;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Component;

//** TV 의 의존성 처리
//=> TV 는 여러개의 Speaker 중 선택적으로 사용할 수 있음
//=> Speaker interface 적용 
//=> @Autowired 자동주입

//=> 여러개 중 선택 : 명시적 자동주입 
//@Autowired , @Qualifier("spa")

//** Test1) : spa, spb 생성후 @Autowired 만 한 경우 
//=> 선택가능한 인스턴스가 여러개인 경우
//...UnsatisfiedDependencyException: Error creating bean with name 'tva': Unsatisfied 
//....matching bean but found 2: spa,spb

//** Test2) : spa 만 생성후 @Autowired 만 한 경우 
//=> Type 일치하는 경우가 1개이기 때문에 정상실행

//** Test3) @Qualifier("spa") : 명시적으로 요청했지만 "spa" 가 없는경우
//=> 정확하게 id 가 일치하는 객체를 찾으므로 오류발생 

//=> xml, @ 병행 가능
//Speaker 의 생성은 xml 에 <bean ..> 등록후 Test

// 인터페이스의 경우 객체를 생성할 수 없기때문에 component사용 불가능
interface Speakeri {
	void volumeUp();

	void volumeDown();
} // Speakeri

@Component("spa")
class SpeakerA implements Speakeri {
	public SpeakerA() {
		System.out.println("~~ SpeakerAAA Default 생성자 ~~");
	}

	@Override
	public void volumeUp() {
		System.out.println("~~ SpeakerAAA volumeUp ~~");
	}

	@Override
	public void volumeDown() {
		System.out.println("~~ SpeakerAAA volumeDown ~~");
	}
} // SpeakerA

@Component("spb")
class SpeakerB implements Speakeri {
	public SpeakerB() {
		System.out.println("~~ SpeakerBBB Default 생성자 ~~");
	}

	@Override
	public void volumeUp() {
		System.out.println("~~ SpeakerBBB volumeUp ~~");
	}

	@Override
	public void volumeDown() {
		System.out.println("~~ SpeakerBBB volumeDown ~~");
	}
} // SpeakerB

//1) 고전적 방법 (직접 new : 소스 재컴파일)
//=> SpeakerA, B 교체 : 직접코드에서
@Component("sstv")
class SsTVsi implements TV {

	private Speakeri speaker = new SpeakerA();
	// 직접 생성자 생성 : 선언과 동시에 초기화 하고 있음
	// 즉 생성자 생성과 동시에 스피커가 이미 생성되어진다.

	public SsTVsi() {
		System.out.println("~~ SsTVsi Default 생성자 ~~");
	}

	@Override
	public void powerOn() {
		System.out.println("~~ SsTVsi powerOn ~~");
	}

	@Override
	public void powerOff() {
		System.out.println("~~ SsTVsi powerOff ~~");
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
//=> SpeakerA, B 교체 : @Autowired , @Qualifier
@Component("lgtv")
class LgTVsi implements TV {

//	@Autowired
	@Qualifier("spa")
	private Speakeri speaker;
	private String color;
	private int price;

	public LgTVsi() {
		System.out.println("~~ LgTVsi Default 생성자 ~~");
	}

	public LgTVsi(Speakeri speaker, String color, int price) {
		this.speaker = speaker;
		this.color = color;
		this.price = price;
		System.out.printf("~~ LgTVsi 초기화 생성자 : color=%s, price=%d \n", color, price);
	}

	@Override
	public void powerOn() {
		System.out.println("~~ LgTVsi powerOn ~~");
	}

	@Override
	public void powerOff() {
		System.out.println("~~ LgTVsi powerOff ~~");
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

//3) IOC/DI 
//=> SpeakerA, B 교체 : @Autowired , @Qualifier
@Component("aitv")
class AiTVsi implements TV {

	// 해당 인스턴스를 찾지 못했을 때 Exception을 발생하지 않고 null return

	@Qualifier("spb")
	private Speakeri speaker;
	private String color;
	private int price;

	public AiTVsi() {
		System.out.println("~~ AiTVsi Default 생성자 ~~");
	}

	public void setSpeaker(Speakeri speaker) {
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
		System.out.printf("~~ AiTVsi powerOn : color=%s, price=%d \n", color, price);
	}

	@Override
	public void powerOff() {
		System.out.println("~~ AiTVsi powerOff ~~");
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

public class TVUser07_Speakeri {
	public static void main(String[] args) {
		// 1. 스프링 컨테이너(BeanFactory)생성 : spring에서의 Container(컨테이너)생성
		AbstractApplicationContext sc = new GenericXmlApplicationContext("iocDI02_anno/app05.xml");

		// 2. 필요한 객체를 전달받아 서비스 실행
		System.out.println("** 1) 고전적 방법 (직접 new : 소스 재컴파일) **");
		TV sstv = (TV) sc.getBean("sstv"); // xml 파일의 id로 호출
		sstv.powerOn();
		sstv.volumeUp();
		sstv.volumeDown();
		sstv.powerOff();

		System.out.println("SpeakerA, B 교체 : @Autowired , @Qualifier");

		System.out.println("** 2) IOC/DI **");
		TV lgtv = (TV) sc.getBean("lgtv");
		lgtv.powerOn();
		lgtv.volumeUp();
		lgtv.volumeDown();
		lgtv.powerOff();

		System.out.println("** 3) IOC/DI -> setter 주입 **");
		TV aitv = (TV) sc.getBean("aitv");
		aitv.powerOn();
		aitv.volumeUp();
		aitv.volumeDown();
		aitv.powerOff();

		sc.close();
	}
}