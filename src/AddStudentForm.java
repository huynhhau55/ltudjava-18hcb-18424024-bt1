import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AddStudentForm {

	private JFrame frmAddSd;
	private JTable table;
	private JTextField txtMSSV;
	private JTextField txtHoTen;
	private JLabel lblCmnd;
	private JTextField txtCMND;
	private JLabel lblGioiTinh;
	private JButton btnThem;
	private JButton btnQuayLai;
	private JScrollPane scrollPane;
	private JComboBox<String> cbbLop;
	
	
	
	

	/**
	 * Launch the application.
	 */
	
	public JComboBox<String> getCbbLop(){
	
		return this.cbbLop;
	}
	
	public JFrame getFrmAddSd() {
		
		return this.frmAddSd;
	}
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddStudentForm window = new AddStudentForm();
					window.frmAddSd.setLocationRelativeTo(null);
					window.frmAddSd.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AddStudentForm() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void loadSinhVien() {
		
		try {
			
			String filePath = cbbLop.getSelectedItem() + ".csv";
			BufferedReader br = new BufferedReader(new FileReader(new File(filePath)));
			List<String[]> elements = new ArrayList<String[]>();
			String line = null;
			while((line = br.readLine()) != null ) {
				
				String[] spiltted = line.split(";");
				elements.add(spiltted);
			
			}
			br.close();
			String[] columsName = new String[] {
					"STT","MSSV","Ho Ten","Gioi Tinh", "CMND", "Lop"							
			};
			Object[][] content = new Object[elements.size()][6];
			for(int i = 0; i < elements.size(); i++) {
				for(int j = 0; j < 6; j++) {
					
					content[i][j] = elements.get(i)[j];
				}
			}
			table.setModel(new DefaultTableModel(content,columsName));
		}catch (Exception e2) {
			
			e2.printStackTrace();
			
			}
		
		
	}
	private void initialize() {
		frmAddSd = new JFrame();
		frmAddSd.setTitle("Add Student");
		frmAddSd.setBounds(100, 100, 1107, 660);
		frmAddSd.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAddSd.getContentPane().setLayout(null);
		
		
		
		
		txtMSSV = new JTextField();
		txtMSSV.setBounds(208, 104, 146, 29);
		frmAddSd.getContentPane().add(txtMSSV);
		txtMSSV.setColumns(10);
		
		JLabel lblMssv = new JLabel("MSSV");
		lblMssv.setBounds(131, 106, 69, 25);
		frmAddSd.getContentPane().add(lblMssv);
		
		JLabel lblLop = new JLabel("L\u1EDBp");
		lblLop.setBounds(131, 63, 69, 25);
		frmAddSd.getContentPane().add(lblLop);
		
		JLabel lblHoTen = new JLabel("H\u1ECD t\u00EAn");
		lblHoTen.setBounds(406, 63, 69, 25);
		frmAddSd.getContentPane().add(lblHoTen);
		
		txtHoTen = new JTextField();
		txtHoTen.setColumns(10);
		txtHoTen.setBounds(508, 61, 146, 29);
		frmAddSd.getContentPane().add(txtHoTen);
		
		lblCmnd = new JLabel("CMND");
		lblCmnd.setBounds(406, 106, 69, 25);
		frmAddSd.getContentPane().add(lblCmnd);
		
		txtCMND = new JTextField();
		txtCMND.setColumns(10);
		txtCMND.setBounds(508, 104, 146, 29);
		frmAddSd.getContentPane().add(txtCMND);
		
		lblGioiTinh = new JLabel("Gi\u1EDBi t\u00EDnh");
		lblGioiTinh.setBounds(694, 59, 69, 29);
		frmAddSd.getContentPane().add(lblGioiTinh);
		
		JComboBox<String> cbbGioiTinh = new JComboBox<String>();
		cbbGioiTinh.setBounds(799, 60, 146, 26);
		frmAddSd.getContentPane().add(cbbGioiTinh);
		cbbGioiTinh.addItem("Nam");
		cbbGioiTinh.addItem("Nu");
		
		btnThem = new JButton("Th\u00EAm");
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					String filePath = cbbLop.getSelectedItem().toString() + ".csv";
					List<Student> Students = Student.readStudents(filePath);
					String stt = Integer.toString(Students.size());
					String gioiTinh = String.valueOf(cbbGioiTinh.getSelectedItem());
					Student sd = new Student(stt, txtMSSV.getText(), txtHoTen.getText(), 
										gioiTinh,txtCMND.getText() , cbbLop.getSelectedItem().toString());
					FileWriter fw = new FileWriter(filePath,true);
					BufferedWriter bw = new BufferedWriter(fw);
					PrintWriter pw = new PrintWriter(bw);
					pw.println(sd.sttStudent + ";" + sd.studentID + ";" + sd.nameStudent + ";" + 
							   sd.genderStudent + ";" + sd.identityCard + ";" + sd.classRoom );
					
					pw.close();
					loadSinhVien();
					txtCMND.setText("");
					txtHoTen.setText("");
					txtMSSV.setText("");
					
					
				}
				catch(Exception ioe) {
					
					ioe.printStackTrace();
				}	
				
			}
		});
		btnThem.setBounds(131, 158, 156, 43);
		frmAddSd.getContentPane().add(btnThem);
		
		btnQuayLai = new JButton("Quay l\u1EA1i");
		btnQuayLai.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnQuayLai.setBounds(799, 158, 146, 43);
		frmAddSd.getContentPane().add(btnQuayLai);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(15, 217, 1055, 371);
		frmAddSd.getContentPane().add(scrollPane);
		
	
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				DefaultTableModel dfModel = (DefaultTableModel)table.getModel();
				int selectedRowIndex = table.getSelectedRow();
				txtMSSV.setText(dfModel.getValueAt(selectedRowIndex, 1).toString());
				txtHoTen.setText(dfModel.getValueAt(selectedRowIndex, 2).toString());
				cbbGioiTinh.setSelectedItem(dfModel.getValueAt(selectedRowIndex, 3).toString());
				txtCMND.setText(dfModel.getValueAt(selectedRowIndex, 4).toString());
				cbbLop.setSelectedItem(dfModel.getValueAt(selectedRowIndex, 5).toString());
				
			}
		});
		scrollPane.setViewportView(table);
		
		JButton btnXoa = new JButton("X\u00F3a");
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnXoa.setBounds(365, 158, 151, 43);
		frmAddSd.getContentPane().add(btnXoa);
		
		JButton btnCapNhat = new JButton("C\u1EADp nh\u1EADt");
		btnCapNhat.setBounds(584, 158, 156, 43);
		frmAddSd.getContentPane().add(btnCapNhat);
		
		cbbLop = new JComboBox<String>();
		cbbLop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				loadSinhVien();
				
			}
		});
		cbbLop.setBounds(208, 61, 146, 28);
		frmAddSd.getContentPane().add(cbbLop);
	
		
	}
}
