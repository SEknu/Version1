/** �׽�Ʈ ���̽� �߰� 
 *  Ŭ���̾�Ʈ, ���α׷�, Ʈ���̳�, ��ⱸ ã�⿡ ���� �׽�Ʈ  **/

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
		client.setName("ȫ�浿");
		client.setTerminateDate("2015-12-01");
		client.setTrainer("Ʈ���̳�");
		client.setProgram("���α׷�");
		//ã�⸦ �Ҹ��� �׸��� �̸�, ������ ��, Ʈ���̳�, ���α׷� �����ϵ�?
		
		filemanager.addClient(client);
		
		Client clientComp1;
		Client clientComp2;
		Client clientComp3;
		Client clientComp4;
		
		clientComp1 = filemanager.selectClient("name", "ȫ�浿");
		clientComp2 = filemanager.selcetClient("terminate_date", "2015-12-01");
		clientComp3 = filemanager.selectClient("trainer", "Ʈ���̳�");
		clientComp4 = filemanager.selectClient("program", "���α׷�");
		//1, 2, 3, 4�� ���� ã�� ���� ��� �ٸ�. ������ ����� ����� �����ϴ� �� �˾ƺ��� ���� ��.
		
		assertThat(clientComp1.getName(), is("ȫ�浿"));
		assertThat(clientComp1.getTerminateDate(), is("2015-12-01"));
		assertThat(clientComp1.getTrainer(), is("Ʈ���̳�"));
		assertThat(clientComp1.getProgram(), is("���α׷�"));
		
		assertThat(clientComp2.getName(), is("ȫ�浿"));
		assertThat(clientComp2.getTerminateDate(), is("2015-12-01"));
		assertThat(clientComp2.getTrainer(), is("Ʈ���̳�"));
		assertThat(clientComp2.getProgram(), is("���α׷�"));
		
		assertThat(clientComp3.getName(), is("ȫ�浿"));
		assertThat(clientComp3.getTerminateDate(), is("2015-12-01"));
		assertThat(clientComp3.getTrainer(), is("Ʈ���̳�"));
		assertThat(clientComp3.getProgram(), is("���α׷�"));
		
		assertThat(clientComp4.getName(), is("ȫ�浿"));
		assertThat(clientComp4.getTerminateDate(), is("2015-12-01"));
		assertThat(clientComp4.getTrainer(), is("Ʈ���̳�"));
		assertThat(clientComp4.getProgram(), is("���α׷�"));
	}
	
	@Test
	public void selectProgram() {
		Program program = new Program();
		program.setName("�");
		program.setDifficulty("����");
		program.setPartOfBody("�ٸ�");
		//���α׷����� ��̸�, ���̵�, ���� ������ �˻��� �� �Ͱ���.
		
		Program programComp1;
		Program programComp2;
		Program programComp3;
		
		filemanager.addProgram(program);
		
		programComp1 = filemanager.selectProgram("name", is("�"));
		programComp2 = filemanager.selectProgram("difficulty", is("����"));
		programComp3 = filemanager.selectProgram("part_of_body", is("�ٸ�"));
		//1, 2, 3 ���� ã�� ���� �ϳ��� ������ ���� �׽�Ʈ ����
		
		assertThat(programComp1.getName(), is("�"));
		assertThat(programComp1.getDifficulty(), is("����"));
		assertThat(programComp1.getPartOfBody(), is("�ٸ�"));
		
		assertThat(programComp2.getName(), is("�"));
		assertThat(programComp2.getDifficulty(), is("����"));
		assertThat(programComp2.getPartOfBody(), is("�ٸ�"));
		
		assertThat(programComp3.getName(), is("�"));
		assertThat(programComp3.getDifficulty(), is("����"));
		assertThat(programComp3.getPartOfBody(), is("�ٸ�"));
	}
	
	@Test
	public void selectCommodity() throws ClassNotFoundException, SQLException {	
		Commodity commodity = new Commodity();
		commodity.setName("��ⱸ");
		commodity.setInventory(1);
		commodity.setState(0);
		//��ⱸ�� �̸�, ���, ���¸� �˻� �غ� �� �����ʴ�..? ����������
		
		Commodity commodityComp1;
		Commodity commodityComp2;
		Commodity commodityComp3;
		
		filemanager.addCommodity(commodity);
	
		commodityComp1 = filemanager.selectCommodity("name", "��ⱸ");
		commodityComp2 = filemanager.selectCommodity("inventory", "1");
		commodityComp3 = filemanager.selectCommodity("state", "0");
		
		assertThat(commodityComp1.getName(), is("��ⱸ"));
		assertThat(commodityComp1.getInventory(), is(1));
		assertThat(commodityComp1.getState(), is(0));
		
		assertThat(commodityComp2.getName(), is("��ⱸ"));
		assertThat(commodityComp2.getInventory(), is(1));
		assertThat(commodityComp2.getState(), is(0));
		
		assertThat(commodityComp3.getName(), is("��ⱸ"));
		assertThat(commodityComp3.getInventory(), is(1));
		assertThat(commodityComp3.getState(), is(0));
	}
	
	@Test
	public void selectTrainer() throws ClassNotFoundException, SQLException {	
		Trainer trainer = new Trainer();
		trainer.setName("Ʈ���̳�");
		//�̸������� �ʿ����� ������?
		
		Trainer trainerComp1;
		
		filemanager.addTrainer(trainer);
		
		trainerComp1 = filemanager.selectTrainer("name", "Ʈ���̳�");
		
		assertThat(trainerComp1.getName(), is("Ʈ���̳�"));
	}
	
}
