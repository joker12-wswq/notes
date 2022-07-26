

--------->7.13

## AWT事件模型

<img src="E:/Program Files/Typora/img/image-20220713095038846.png" alt="image-20220713095038846" style="zoom: 80%;" />

事件源：产生事件的对象

事件：改变事件源状态的对象

#### WindowListener

1.窗口监听事件接口，七个方法

~~~
windowActivated、windowDeactivated 
windowClosed、windowClosing
windowIconified、windowDeiconified
windowOpened 
~~~

2.窗口适配器WindowAdapter，对WindowListener作了空实现

可以只对一个方法做实现，而不用必须给7个方法做实现

~~~
addWindowListener(new WindowAdapter(){
	
       public void windowClosing(WindowEvent wevent)
	  {
        System.exit(0);
      }
  
	}
);
~~~

#### ActionListener

1.对Button、List、MenuItem、TextField 做监听

2.对按钮做监听实例

实现ActionListener接口，重写actionPerformed方法

~~~
JButton ok = new JButton("注册");
ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
				代码
        });
~~~

~~~
按钮名.addActionListener((event)->代码));
~~~

#### 焦点事件

1.FocusListener

2.需要实现focusLost、focusGained两个方法

~~~
TextField tf1 = new TextField();
tf1.addFocusListener(new FocusListener1());
~~~

~~~~
class FocusListener1 implements FocusListener{
    //焦点丢失
    public void focusLost(FocusEvent e){
    	TextField tf = (TextField)e.getSource();
    	if(!tf.getText().equals("tom")){
    	    System.out.println("Invalidate");//无效
    	    tf.setText("");//赋空
			tf.requestFocus();
    	}
    	else{
    	    System.out.println("validate");//有效
    	}
    }
   //焦点获得
    public void focusGained(FocusEvent e){
    }
}
~~~~

#### 键盘事件

1.KeyListener，在按下或释放键盘上的一个键时，将==生成键盘事件==

2.创建使用，需要实现3个方法：keyPressed、keyReleased、keyTyped，继承键盘适配器

~~~
   TextField tField = new TextField(20);
   add(tField);  
   MyKeyAdapter bAction = new MyKeyAdapter();
   tField.addKeyListener(bAction);
~~~

~~~
 private class MyKeyAdapter extends KeyAdapter {
      //按键盘
	  public void keyPressed(KeyEvent kevent){
	     //改变颜色
         setBackground(Color.blue); repaint(); }
      //离开键盘
	  public void keyReleased(KeyEvent kevent) {
         setBackground(Color.red); repaint();  }
      //
	  public void keyTyped(KeyEvent kevent) {
        System.out.println(kevent.getKeyChar());
		if (kevent.getKeyChar() == 'x')  System.exit(0); }
   }
~~~

3.实现键盘监听

~~~
 class KeyListener1 implements KeyListener{
    public void keyPressed(KeyEvent e){
    	int keyCode = e.getKeyCode();//拿到键码
    	if(keyCode == e.VK_RIGHT && e.isShiftDown()){
    	    System.out.println("key Right and Shift is Pressed ");
    	}
    	else{
    	    System.out.println("keyPressed " + keyCode);
    	}
    }

    public void keyReleased(KeyEvent e){
    	int keyCode = e.getKeyCode();
    	System.out.println("keyReleased " + keyCode);
    }

    public void keyTyped(KeyEvent e){
    	//int keyCode = e.getKeyCode();
    	System.out.println("keyTyped " + e.getKeyChar());
    }
}

~~~

#### 鼠标事件

1.输入框有默认文字提示，点击消失

~~~
	Container con=getContentPane();
	con.setLayout(new FlowLayout());
	label1=new JLabel("用户名：");
	userName=new JTextField("--请输入用户名--");
	//鼠标适配器，重写鼠标点击事件
	userName.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent arg0) {
				if(userName.getText().equals("--请输入用户名--")){
					userName.setText("");
				}
			}
		});
~~~

2.鼠标事件有两种监听器：MouseListener 和 MouseMotionListener。

需要实现的方法如下：    mouseClicked、 mouseEntered、mouseExited、mousePressed、mouseReleased、mouseDragged、mouseMoved 

3.在按下或释放键盘上的一个键时，将==生成键盘事件==。

#### Item事件

1.Item事件监听器： ItemListener 。需要实现的方法: itemStateChanged

2.单击复选框或列表项时，或者当一个选择框或一个可选菜单的项被选择或取消时==生成此事件==

3.使用：实现ItemListener接口，重写itemStateChanged方法

~~~~
public class Qualification extends Frame implements ItemListener{
   
public void itemStateChanged(ItemEvent e) {
		System.out.println("hello");
	}
~~~~

## swing

相对于AWT：

1.自动对窗口事件做处理，不需要对窗口事件做监听

2.可以在标签、按钮上加图标

3.可以设置快捷键

4.增加了单选框radiobutton

#### 菜单

1.只能放在框架上，不能放在面板上，不能设置布局

2.菜单栏menubar、菜单项menu items、菜单menu、jcheckboxmenuitems复选菜单项

间隔addseparator、setmenubar

3.JOptionPane       对话框组件

[JOptionPane的用法]: https://mbd.baidu.com/ma/s/ZapT2D5U

