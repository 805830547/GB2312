package gb2312;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class View extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    JPanel jpanel;  
    JTextArea jta1 = null,jta2 = null,jta3 = null; 
    JScrollPane jscrollPane1,jscrollPane2,jscrollPane3; 
	JButton jb1 = new JButton("转换");
	JButton jb2 = new JButton("转换");
	JButton jb3 = new JButton("转换");
	JLabel jlb1,jlb2,jlb3;
	JLabel byLY,toAN;
	JOptionPane jop = new JOptionPane();
	
	public View(){
		this.setVisible(true);
		this.setTitle("GB2312");//设置标题
		this.setResizable(false);//不可改变大小
		this.setLocation(300, 90);
		this.setSize(380, 400);//窗体大小，可以使用FastStone Capture的屏幕尺量一下win计算器的大小
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//默认关闭操作
		
		initContent();
		}
	
	private void initContent(){
		//整个窗口区域

		
		jlb1 = new JLabel("请输入要转换的汉字：");
		jlb1.setFont(new Font("楷体", Font.BOLD, 20)); 
		jlb2 = new JLabel("GB2312编码为：");
		jlb2.setFont(new Font("楷体", Font.BOLD, 20)); 
		jlb3 = new JLabel("二进制表示：");
		jlb3.setFont(new Font("楷体", Font.BOLD, 20));
		
		byLY = new JLabel("@author LY");
		byLY.setFont(new Font("楷体", Font.BOLD, 16)); 
		toAN = new JLabel("@for AN");
		toAN.setFont(new Font("楷体", Font.BOLD, 16)); 
		
		jta1 = new JTextArea();  
        jta1.setTabSize(4);  
        jta1.setFont(new Font("楷体", Font.BOLD, 20));  
        jta1.setLineWrap(true);// 激活自动换行功能  
        jta1.setWrapStyleWord(true);// 激活断行不断字功能  
  
        jscrollPane1 = new JScrollPane(jta1); 
        jta1.setBounds(0, 0, 250, 30);

        
		jta2 = new JTextArea();  
        jta2.setTabSize(4);  
        jta2.setFont(new Font("楷体", Font.BOLD, 20));  
        jta2.setLineWrap(true);// 激活自动换行功能  
        jta2.setWrapStyleWord(true);// 激活断行不断字功能  
  
        jscrollPane2 = new JScrollPane(jta2); 
        jta2.setBounds(0, 0, 250, 30);
        
		jta3 = new JTextArea();  
        jta3.setTabSize(4);  
        jta3.setFont(new Font("楷体", Font.BOLD, 20));  
        jta3.setLineWrap(true);// 激活自动换行功能  
        jta3.setWrapStyleWord(true);// 激活断行不断字功能  
  
        jscrollPane3 = new JScrollPane(jta3); 
        jta3.setBounds(0, 0, 250, 30);
        
        jpanel = new JPanel();  
        jpanel.setLayout(null);
        
        jpanel.add(jlb1);  
        jpanel.add(jlb2);  
        jpanel.add(jlb3); 
        
        jlb1.setBounds(10,0,280,20);
        jlb2.setBounds(10,70,280,20);
        jlb3.setBounds(10,170,280,20);
        
 
        jpanel.add(jscrollPane1);
        jpanel.add(jscrollPane2);
        jpanel.add(jscrollPane3);
        jscrollPane1.setBounds(10, 20, 250, 30);
        jscrollPane2.setBounds(10, 90, 250, 60);
        jscrollPane3.setBounds(10, 190, 250, 100);
        
        jb1.setFont(new Font("楷体", Font.BOLD, 20));
        jb2.setFont(new Font("楷体", Font.BOLD, 20));
        jb3.setFont(new Font("楷体", Font.BOLD, 20));
        
        
        jpanel.add(jb1);
        jpanel.add(jb2);
        jpanel.add(jb3);
        jb1.addActionListener(this);
        jb2.addActionListener(this);
        jb3.addActionListener(this);
        jb1.setBounds(280, 20, 80, 30);
        jb2.setBounds(280, 90, 80, 60);
        jb3.setBounds(280, 190, 80, 100);
        
        this.add(byLY);
        this.add(toAN);
        this.add(jpanel);
        jpanel.setBounds(0, 40, 380, 300);
        byLY.setBounds(260, 340, 100, 20);
        toAN.setBounds(15, 10, 100, 20);
		
	}
	
	private void clickJb1() {
		String jta1String = jta1.getText();
		if (jta1String.contains(" ")) {
			JOptionPane.showMessageDialog( null , "不许输入空格" ,"不许调皮" , JOptionPane.WARNING_MESSAGE) ;
		} else if (jta1String.contains("\n")) {
			JOptionPane.showMessageDialog( null , "输入回车干嘛？ 上边不是有按钮吗！" ,"不许调皮" , JOptionPane.WARNING_MESSAGE) ;
		} else if (!CharUtil.isChinese(jta1String)) {
			JOptionPane.showMessageDialog( null , "又不是汉字，我才不转换呢！哼！" ,"不许调皮" , JOptionPane.WARNING_MESSAGE) ;
		} else {
			GB2312 gb2312 = new GB2312();
			String jta2String = gb2312.convertGB2312StringToGB2312Code(jta1String);
			jta2.setText(jta2String);
			MumConvert aConvert = new MumConvert();
			String jta3String = aConvert.integerToBinary(jta2String);
			jta3.setText(jta3String);
		}
	}
	private void clickJb2() {
		String jta2String = jta2.getText();

		GB2312 gb2312 = new GB2312();
		String jta1String = gb2312.convertGB2312CodeToGB2312String(jta2String);
		jta1.setText(jta1String);
		MumConvert aConvert = new MumConvert();
		String jta3String = aConvert.integerToBinary(jta2String);
		jta3.setText(jta3String);

	}
	private void clickJb3() {
		String jta3String = jta3.getText();

			MumConvert aConvert = new MumConvert();
			String jta2String = aConvert.binaryToInteger(jta3String);
			jta2.setText(jta2String);
			GB2312 gb2312 = new GB2312();
			String jta1String = gb2312.convertGB2312CodeToGB2312String(jta2String);
			jta1.setText(jta1String);

	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource().equals(jb1)) {
			clickJb1();
		} else if (e.getSource().equals(jb2)) {
			clickJb2();
		} else if (e.getSource().equals(jb3)) {
			clickJb3();
		}

	}

}
