/** �׽�Ʈ ���̽� �߰� 
 *  Ŭ���̾�Ʈ, ���α׷�, Ʈ���̳�, ��ⱸ ã�⿡ ���� �׽�Ʈ  **/

package test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.sql.SQLException;
import java.util.Vector;

import object.Client;
import object.Commodity;
import object.Program;
import object.Trainer;

import org.junit.Test;

import database.FileManager;

public class test {

	FileManager filemanager = FileManager.getInstance();
	
	@Test
	public void selectClient() throws ClassNotFoundException, SQLException {
		Client client = new Client();
		client.setName("ȫ�浿");
		client.setTerminateDate("2015-12-01");
		client.setTrainer("Ʈ���̳�");
		client.setProgram("���α׷�");
		//ã�⸦ �Ҹ��� �׸��� �̸�, ������ ��, Ʈ���̳�, ���α׷� �����ϵ�?
		
		filemanager.add(client);
		
		Vector<Client> clientComp1;
		Vector<Client> clientComp2;
		Vector<Client> clientComp3;
		Vector<Client> clientComp4;
		
		clientComp1 = filemanager.selectClient("name", "ȫ�浿");
		clientComp2 = filemanager.selectClient("terminate_date", "2015-12-01");
		clientComp3 = filemanager.selectClient("trainer", "Ʈ���̳�");
		clientComp4 = filemanager.selectClient("program", "���α׷�");
		//1, 2, 3, 4�� ���� ã�� ���� ��� �ٸ�. ������ ����� ����� �����ϴ� �� �˾ƺ��� ���� ��.
		
		assertThat(clientComp1.elementAt(0).getName(), is("ȫ�浿"));
		assertThat(clientComp1.elementAt(0).getTerminateDate(), is("2015-12-01"));
		assertThat(clientComp1.elementAt(0).getTrainer(), is("Ʈ���̳�"));
		assertThat(clientComp1.elementAt(0).getProgram(), is("���α׷�"));
		
		assertThat(clientComp2.elementAt(0).getName(), is("ȫ�浿"));
		assertThat(clientComp2.elementAt(0).getTerminateDate(), is("2015-12-01"));
		assertThat(clientComp2.elementAt(0).getTrainer(), is("Ʈ���̳�"));
		assertThat(clientComp2.elementAt(0).getProgram(), is("���α׷�"));
		
		assertThat(clientComp3.elementAt(0).getName(), is("ȫ�浿"));
		assertThat(clientComp3.elementAt(0).getTerminateDate(), is("2015-12-01"));
		assertThat(clientComp3.elementAt(0).getTrainer(), is("Ʈ���̳�"));
		assertThat(clientComp3.elementAt(0).getProgram(), is("���α׷�"));
		
		assertThat(clientComp4.elementAt(0).getName(), is("ȫ�浿"));
		assertThat(clientComp4.elementAt(0).getTerminateDate(), is("2015-12-01"));
		assertThat(clientComp4.elementAt(0).getTrainer(), is("Ʈ���̳�"));
		assertThat(clientComp4.elementAt(0).getProgram(), is("���α׷�"));
	}
	
	@Test
	public void selectProgram() throws ClassNotFoundException, SQLException {
		Program program = new Program();
		program.setID("1");
		program.setName("�");
		program.setDifficulty("����");
		program.setPartOfBody("�ٸ�");
		//���α׷����� ��̸�, ���̵�, ���� ������ �˻��� �� �Ͱ���.
		
		Vector<Program> programComp1;
		Vector<Program> programComp2;
		Vector<Program> programComp3;
		
		filemanager.add(program);
		
		programComp1 = filemanager.selectProgram("name", "�");
		programComp2 = filemanager.selectProgram("difficulty", "����");
		programComp3 = filemanager.selectProgram("part_of_body", "�ٸ�");
		//1, 2, 3 ���� ã�� ���� �ϳ��� ������ ���� �׽�Ʈ ����
		
		assertThat(programComp1.elementAt(0).getName(), is("�"));
		assertThat(programComp1.elementAt(0).getDifficulty(), is("����"));
		assertThat(programComp1.elementAt(0).getPartOfBody(), is("�ٸ�"));
		
		assertThat(programComp2.elementAt(0).getName(), is("�"));
		assertThat(programComp2.elementAt(0).getDifficulty(), is("����"));
		assertThat(programComp2.elementAt(0).getPartOfBody(), is("�ٸ�"));
		
		assertThat(programComp3.elementAt(0).getName(), is("�"));
		assertThat(programComp3.elementAt(0).getDifficulty(), is("����"));
		assertThat(programComp3.elementAt(0).getPartOfBody(), is("�ٸ�"));
		
	}
	
	@Test
	public void selectCommodity() throws ClassNotFoundException, SQLException {	
		Commodity commodity = new Commodity();
		commodity.setName("��ⱸ");
		commodity.setInventory(1);
		commodity.setState(0);
		//��ⱸ�� �̸�, ���, ���¸� �˻� �غ� �� �����ʴ�..? ����������
		
		Vector<Commodity> commodityComp1;
		Vector<Commodity> commodityComp2;
		Vector<Commodity> commodityComp3;
		
		filemanager.add(commodity);
	
		commodityComp1 = filemanager.selectCommodity("name", "��ⱸ");
		commodityComp2 = filemanager.selectCommodity("inventory", "1");
		commodityComp3 = filemanager.selectCommodity("state", "0");
		
		assertThat(commodityComp1.elementAt(0).getName(), is("��ⱸ"));
		assertThat(commodityComp1.elementAt(0).getInventory(), is(1));
		assertThat(commodityComp1.elementAt(0).getState(), is(0));
		
		assertThat(commodityComp2.elementAt(0).getName(), is("��ⱸ"));
		assertThat(commodityComp2.elementAt(0).getInventory(), is(1));
		assertThat(commodityComp2.elementAt(0).getState(), is(0));
		
		assertThat(commodityComp3.elementAt(0).getName(), is("��ⱸ"));
		assertThat(commodityComp3.elementAt(0).getInventory(), is(1));
		assertThat(commodityComp3.elementAt(0).getState(), is(0));
		
	}
	
	@Test
	public void selectTrainer() throws ClassNotFoundException, SQLException {	
		Trainer trainer = new Trainer();
		trainer.setName("Ʈ���̳�");
		//�̸������� �ʿ����� ������?
		
		Vector<Trainer> trainerComp1;
		
		filemanager.add(trainer);
		
		trainerComp1 = filemanager.selectTrainer("name", "Ʈ���̳�");
		
		assertThat(trainerComp1.elementAt(0).getName(), is("Ʈ���̳�"));
	}
	
}
