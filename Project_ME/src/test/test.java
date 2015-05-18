/** 테스트 케이스 추가 
 *  클라이언트, 프로그램, 트레이너, 운동기구 찾기에 대한 테스트  **/

package test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.sql.SQLException;

import object.Client;
import object.Commodity;
import object.Program;
import object.Trainer;

import org.junit.Test;

import database.FileManager;

public class test {

	FileManager filemanager = FileManager.getInstance();
	
	@Test
	public void selectClient() {
		Client client = new Client();
		client.setName("홍길동");
		client.setTerminateDate("2015-12-01");
		client.setTrainer("트레이너");
		client.setProgram("프로그램");
		//찾기를 할만한 항목이 이름, 끝나는 날, 트레이너, 프로그램 정도일듯?
		
		filemanager.addClient(client);
		
		Client clientComp1;
		Client clientComp2;
		Client clientComp3;
		Client clientComp4;
		
		clientComp1 = filemanager.selectClient("name", "홍길동");
		clientComp2 = filemanager.selcetClient("terminate_date", "2015-12-01");
		clientComp3 = filemanager.selectClient("trainer", "트레이너");
		clientComp4 = filemanager.selectClient("program", "프로그램");
		//1, 2, 3, 4에 따라 찾는 것이 모두 다름. 각각의 기능을 제대로 수행하는 지 알아보기 위한 것.
		
		assertThat(clientComp1.getName(), is("홍길동"));
		assertThat(clientComp1.getTerminateDate(), is("2015-12-01"));
		assertThat(clientComp1.getTrainer(), is("트레이너"));
		assertThat(clientComp1.getProgram(), is("프로그램"));
		
		assertThat(clientComp2.getName(), is("홍길동"));
		assertThat(clientComp2.getTerminateDate(), is("2015-12-01"));
		assertThat(clientComp2.getTrainer(), is("트레이너"));
		assertThat(clientComp2.getProgram(), is("프로그램"));
		
		assertThat(clientComp3.getName(), is("홍길동"));
		assertThat(clientComp3.getTerminateDate(), is("2015-12-01"));
		assertThat(clientComp3.getTrainer(), is("트레이너"));
		assertThat(clientComp3.getProgram(), is("프로그램"));
		
		assertThat(clientComp4.getName(), is("홍길동"));
		assertThat(clientComp4.getTerminateDate(), is("2015-12-01"));
		assertThat(clientComp4.getTrainer(), is("트레이너"));
		assertThat(clientComp4.getProgram(), is("프로그램"));
	}
	
	@Test
	public void selectProgram() {
		Program program = new Program();
		program.setName("운동");
		program.setDifficulty("쉬움");
		program.setPartOfBody("다리");
		//프로그램에선 운동이름, 난이도, 부위 정도를 검색해 볼 것같음.
		
		Program programComp1;
		Program programComp2;
		Program programComp3;
		
		filemanager.addProgram(program);
		
		programComp1 = filemanager.selectProgram("name", is("운동"));
		programComp2 = filemanager.selectProgram("difficulty", is("쉬움"));
		programComp3 = filemanager.selectProgram("part_of_body", is("다리"));
		//1, 2, 3 각각 찾는 것을 하나씩 나누어 가저 테스트 진행
		
		assertThat(programComp1.getName(), is("운동"));
		assertThat(programComp1.getDifficulty(), is("쉬움"));
		assertThat(programComp1.getPartOfBody(), is("다리"));
		
		assertThat(programComp2.getName(), is("운동"));
		assertThat(programComp2.getDifficulty(), is("쉬움"));
		assertThat(programComp2.getPartOfBody(), is("다리"));
		
		assertThat(programComp3.getName(), is("운동"));
		assertThat(programComp3.getDifficulty(), is("쉬움"));
		assertThat(programComp3.getPartOfBody(), is("다리"));
	}
	
	@Test
	public void selectCommodity() throws ClassNotFoundException, SQLException {	
		Commodity commodity = new Commodity();
		commodity.setName("운동기구");
		commodity.setInventory(1);
		commodity.setState(0);
		//운동기구의 이름, 재고량, 상태를 검색 해볼 것 같지않니..? ㅎㅎㅎㅎㅎ
		
		Commodity commodityComp1;
		Commodity commodityComp2;
		Commodity commodityComp3;
		
		filemanager.addCommodity(commodity);
	
		commodityComp1 = filemanager.selectCommodity("name", "운동기구");
		commodityComp2 = filemanager.selectCommodity("inventory", "1");
		commodityComp3 = filemanager.selectCommodity("state", "0");
		
		assertThat(commodityComp1.getName(), is("운동기구"));
		assertThat(commodityComp1.getInventory(), is(1));
		assertThat(commodityComp1.getState(), is(0));
		
		assertThat(commodityComp2.getName(), is("운동기구"));
		assertThat(commodityComp2.getInventory(), is(1));
		assertThat(commodityComp2.getState(), is(0));
		
		assertThat(commodityComp3.getName(), is("운동기구"));
		assertThat(commodityComp3.getInventory(), is(1));
		assertThat(commodityComp3.getState(), is(0));
	}
	
	@Test
	public void selectTrainer() throws ClassNotFoundException, SQLException {	
		Trainer trainer = new Trainer();
		trainer.setName("트레이너");
		//이름정도만 필요하지 않을까?
		
		Trainer trainerComp1;
		
		filemanager.addTrainer(trainer);
		
		trainerComp1 = filemanager.selectTrainer("name", "트레이너");
		
		assertThat(trainerComp1.getName(), is("트레이너"));
	}
	
}
