# Задание 11
 
## Java: графическое приложение, обработка событий
Простейшее оконное приложение:

    import java.awt.*;
    import javax.swing.*;
    
    public class SimpleJFrameExample {
      public static void main(String[] args) {
          //Create and set up the window.
          JFrame frame = new JFrame("SimpleJFrameExample");
          frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     
          JLabel emptyLabel = new JLabel("");
          emptyLabel.setPreferredSize(new Dimension(175, 100));
          frame.getContentPane().add(emptyLabel, BorderLayout.CENTER);
    
          //Display the window.
          frame.pack();
          frame.setVisible(true);
      }
    } 
Обработка щелчка по командной кнопке:

    import java.awt.event.*;
    import java.awt.*;
    import javax.swing.*;
    
    public class JButtonExample extends JFrame
                            implements ActionListener {
        protected JButton b1;
    
        public JButtonExample(String title) {
            super(title);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
            b1 = new JButton("Green");
    
            //Listen for actions on button 1.
            b1.addActionListener(this);
    
            //Add Components to this container, using the default FlowLayout.
            getContentPane().setLayout(new FlowLayout());
            getContentPane().add(b1);
    
            pack();
          }
    
        public void actionPerformed(ActionEvent e) {
            if (e.getSource()==b1) {
                getContentPane().setBackground(Color.GREEN);
            }
        }
    
        public static void main(String[] args) {
            JButtonExample frame = new JButtonExample("JButtonExample");
            frame.setSize(300, 100);
            frame.setVisible(true);
        }
    }

1. Создать фрейм с областью для рисования (мышь оставляет след). Добавить кнопки для выбора одного из трех цветов пера. Картинка разноцветная.
2. Осуществить рисование на панели со скроллингом.
3. Реализовать диалог сохранения/открытия файла в формате картинки (использовать класс `ImageIO`).
