import javax.swing.*;
import java.awt.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.InputEvent;

import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.util.Locale;

import java.util.ArrayList;
import java.util.List;

public class Main {
	
	private static JTextField textField;
	static JDesktopPane desktopPane = new JDesktopPane();
	static List<String> recentExamples = new ArrayList<>();
	static int fontSize = 40;
	static BigDecimal memoryValue = BigDecimal.ZERO;
    
    public static void main(String[] args) {

    	
    	final Color greyClr = new Color(44, 44, 44);
    	
        JFrame frame = new JFrame("");
        frame.setTitle("Калькулятор\r\n");
        frame.setBackground(new Color(32, 32, 32));
        frame.getContentPane().setBackground(new Color(32, 32, 32));
        frame.setSize(345, 570);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        
        textField = new JTextField();
        textField.setFocusable(false);
        textField.setEditable(false);
        textField.setBorder(null);
        textField.setForeground(new Color(255, 255, 255));
        textField.setText("0");
        textField.setHorizontalAlignment(SwingConstants.RIGHT);
        textField.setFont(new Font("Segoe UI", Font.PLAIN, 42));
        textField.setBounds(10, 10, 310, 59);
        frame.getContentPane().add(textField);
        textField.setColumns(10);
        textField.setOpaque(false);

        GridBagLayout gbl_recentPanel = new GridBagLayout();
        JPanel recentPanel = new JPanel(gbl_recentPanel);
        recentPanel.setFocusable(false);
        recentPanel.setBounds(0, 71, 331, 462);
        frame.getContentPane().add(recentPanel);
        recentPanel.setBackground(greyClr);
        recentPanel.setVisible(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = new Insets(5, 5, 5, 5);

        
        UIManager.put("Button.select", greyClr);
        
        frame.getContentPane().add(desktopPane);


        JButton button_0 = new JButton("0");
        button_0.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		textField.setText(dropZeroFromTextField(textField));
        		textField.setText(textField.getText() + "0");
        	}
        });
        button_0.setFocusPainted(false);
        button_0.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        button_0.setForeground(new Color(255, 255, 255));
        button_0.setBorderPainted(false);
        button_0.setBackground(new Color(59, 59, 59));
        button_0.setBounds(90, 453, 70, 70);
        frame.getContentPane().add(button_0);
        
        JButton button_dot = new JButton(".");
        button_dot.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if (!checkOnlyOneDotInOperand(textField.getText()))
        			return;
        		textField.setText(textField.getText() + ".");
        	}
        });
        button_dot.setFocusPainted(false);
        button_dot.setVerticalAlignment(SwingConstants.TOP);
        button_dot.setFont(new Font("Segoe UI", Font.PLAIN, 36));
        button_dot.setForeground(new Color(255, 255, 255));
        button_dot.setBorderPainted(false);
        button_dot.setBackground(new Color(59, 59, 59));
        button_dot.setBounds(170, 453, 70, 70);
        frame.getContentPane().add(button_dot);
        
        JButton button_equals = new JButton("=");
        button_equals.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try {
        			String text = textField.getText();
        			textField.setText(String.valueOf(evaluateExpression(textField.getText())));
        			addElementInList(recentExamples, text);
        		}
        		catch(IndexOutOfBoundsException ex) {
        			System.err.println("Out of element length, try write full example");
        		}
        	}
        });
        button_equals.setFocusPainted(false);
        button_equals.setFont(new Font("Segoe UI", Font.PLAIN, 24));
        button_equals.setForeground(new Color(255, 255, 255));
        button_equals.setBorderPainted(false);
        button_equals.setBackground(new Color(59, 59, 59));
        button_equals.setBounds(250, 453, 70, 70);
        frame.getContentPane().add(button_equals);
        
        JButton button_1 = new JButton("1");
        button_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		textField.setText(dropZeroFromTextField(textField));
        		textField.setText(textField.getText() + "1");
        	}
        });
        button_1.setFocusPainted(false);
        button_1.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        button_1.setForeground(new Color(255, 255, 255));
        button_1.setBorderPainted(false);
        button_1.setBackground(new Color(59, 59, 59));
        button_1.setBounds(10, 373, 70, 70);
        frame.getContentPane().add(button_1);
        
        JButton button_2 = new JButton("2");
        button_2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		textField.setText(dropZeroFromTextField(textField));
        		textField.setText(textField.getText() + "2");
        	}
        });
        button_2.setFocusPainted(false);
        button_2.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        button_2.setForeground(new Color(255, 255, 255));
        button_2.setBorderPainted(false);
        button_2.setBackground(new Color(59, 59, 59));
        button_2.setBounds(90, 373, 70, 70);
        frame.getContentPane().add(button_2);
        
        JButton button_3 = new JButton("3");
        button_3.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		textField.setText(dropZeroFromTextField(textField));
        		textField.setText(textField.getText() + "3");
        	}
        });
        button_3.setFocusPainted(false);
        button_3.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        button_3.setForeground(new Color(255, 255, 255));
        button_3.setBorderPainted(false);
        button_3.setBackground(new Color(59, 59, 59));
        button_3.setBounds(170, 373, 70, 70);
        frame.getContentPane().add(button_3);
        
        JButton button_plus = new JButton("+");
        button_plus.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                String text = textField.getText();
                if (text.isEmpty()) {
                    return;
                }

                if (!checkOnlyOneOperator(textField)) {
                    return;
                }

                int lastOperatorIndex = -1;
                for (char operator : "+-*/".toCharArray()) {
                    int index = text.lastIndexOf(operator);
                    if (index > lastOperatorIndex) {
                        lastOperatorIndex = index;
                    }
                }
                
                if (lastOperatorIndex == textField.getText().length() - 1)
                    textField.setText(text.substring(0, lastOperatorIndex) + "+" + text.substring(lastOperatorIndex + 1));
                if (lastOperatorIndex == -1)
                    textField.setText(text + "+");
                
                return;
            }
        });
        button_plus.setFocusPainted(false);
        button_plus.setFont(new Font("Segoe UI", Font.PLAIN, 24));
        button_plus.setForeground(new Color(255, 255, 255));
        button_plus.setBorderPainted(false);
        button_plus.setBackground(new Color(59, 59, 59));
        button_plus.setBounds(250, 373, 70, 70);
        frame.getContentPane().add(button_plus);
        
        JButton button_4 = new JButton("4");
        button_4.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		textField.setText(dropZeroFromTextField(textField));
        		textField.setText(textField.getText() + "4");
        	}
        });
        button_4.setFocusPainted(false);
        button_4.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        button_4.setForeground(new Color(255, 255, 255));
        button_4.setBorderPainted(false);
        button_4.setBackground(new Color(59, 59, 59));
        button_4.setBounds(10, 293, 70, 70);
        frame.getContentPane().add(button_4);
        
        JButton button_5 = new JButton("5");
        button_5.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		textField.setText(dropZeroFromTextField(textField));
        		textField.setText(textField.getText() + "5");
        	}
        });
        button_5.setFocusPainted(false);
        button_5.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        button_5.setForeground(new Color(255, 255, 255));
        button_5.setBorderPainted(false);
        button_5.setBackground(new Color(59, 59, 59));
        button_5.setBounds(90, 293, 70, 70);
        frame.getContentPane().add(button_5);
        
        JButton button_6 = new JButton("6");
        button_6.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		textField.setText(dropZeroFromTextField(textField));
        		textField.setText(textField.getText() + "6");
        	}
        });
        button_6.setFocusPainted(false);
        button_6.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        button_6.setForeground(new Color(255, 255, 255));
        button_6.setBorderPainted(false);
        button_6.setBackground(new Color(59, 59, 59));
        button_6.setBounds(170, 293, 70, 70);
        frame.getContentPane().add(button_6);
        
        JButton button_minus = new JButton("-");
        button_minus.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                String text = textField.getText();
                if (text.isEmpty()) {
                    return;
                }

                if (!checkOnlyOneOperator(textField)) {
                    return;
                }

                int lastOperatorIndex = -1;
                for (char operator : "+-*/".toCharArray()) {
                    int index = text.lastIndexOf(operator);
                    if (index > lastOperatorIndex) {
                        lastOperatorIndex = index;
                    }
                }
                
                if (lastOperatorIndex == textField.getText().length() - 1)
                    textField.setText(text.substring(0, lastOperatorIndex) + "-" + text.substring(lastOperatorIndex + 1));
                if (lastOperatorIndex == -1)
                    textField.setText(text + "-");
                
                return;
            }
        });
        button_minus.setFocusPainted(false);
        button_minus.setFont(new Font("Segoe UI", Font.PLAIN, 24));
        button_minus.setForeground(new Color(255, 255, 255));
        button_minus.setBorderPainted(false);
        button_minus.setBackground(new Color(59, 59, 59));
        button_minus.setBounds(250, 293, 70, 70);
        frame.getContentPane().add(button_minus);
        
        JButton button_7 = new JButton("7");
        button_7.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		textField.setText(dropZeroFromTextField(textField));
        		textField.setText(textField.getText() + "7");
        	}
        });
        button_7.setFocusPainted(false);
        button_7.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        button_7.setForeground(new Color(255, 255, 255));
        button_7.setBorderPainted(false);
        button_7.setBackground(new Color(59, 59, 59));
        button_7.setBounds(10, 213, 70, 70);
        frame.getContentPane().add(button_7);
        
        JButton button_8 = new JButton("8");
        button_8.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		textField.setText(dropZeroFromTextField(textField));
        		textField.setText(textField.getText() + "8");
        	}
        });
        button_8.setFocusPainted(false);
        button_8.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        button_8.setForeground(new Color(255, 255, 255));
        button_8.setBorderPainted(false);
        button_8.setBackground(new Color(59, 59, 59));
        button_8.setBounds(90, 213, 70, 70);
        frame.getContentPane().add(button_8);
        
        JButton button_9 = new JButton("9");
        button_9.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		textField.setText(dropZeroFromTextField(textField));
        		textField.setText(textField.getText() + "9");
        	}
        });
        button_9.setFocusPainted(false);
        button_9.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        button_9.setForeground(new Color(255, 255, 255));
        button_9.setBorderPainted(false);
        button_9.setBackground(new Color(59, 59, 59));
        button_9.setBounds(170, 213, 70, 70);
        frame.getContentPane().add(button_9);
        
        JButton button_multiply = new JButton("X");
        button_multiply.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String text = textField.getText();
                if (text.isEmpty()) {
                    return;
                }

                if (!checkOnlyOneOperator(textField)) {
                    return;
                }

                int lastOperatorIndex = -1;
                for (char operator : "+-*/".toCharArray()) {
                    int index = text.lastIndexOf(operator);
                    if (index > lastOperatorIndex) {
                        lastOperatorIndex = index;
                    }
                }
                
                if (lastOperatorIndex == textField.getText().length() - 1)
                    textField.setText(text.substring(0, lastOperatorIndex) + "*" + text.substring(lastOperatorIndex + 1));
                if (lastOperatorIndex == -1)
                    textField.setText(text + "*");
                
                return;
            }
        });
        button_multiply.setFocusPainted(false);
        button_multiply.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        button_multiply.setForeground(new Color(255, 255, 255));
        button_multiply.setBorderPainted(false);
        button_multiply.setBackground(new Color(59, 59, 59));
        button_multiply.setBounds(250, 213, 70, 70);
        frame.getContentPane().add(button_multiply);
        
        JButton button_sqrt = new JButton("√");
        button_sqrt.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		//textField.setText(String.valueOf(evaluateExpression(textField.getText())));
        		double inputValue = Double.parseDouble(textField.getText());
        		if (inputValue <= 0) {
        			textField.setText("0");
        			return;
        		}
                double result = Math.sqrt(inputValue);
                textField.setText(String.format(Locale.US, "%.8f", result));
        	}
        });
        button_sqrt.setFocusPainted(false);
        button_sqrt.setFont(new Font("Segoe UI", Font.PLAIN, 24));
        button_sqrt.setForeground(new Color(255, 255, 255));
        button_sqrt.setBorderPainted(false);
        button_sqrt.setBackground(new Color(59, 59, 59));
        button_sqrt.setBounds(10, 133, 70, 70);
        frame.getContentPane().add(button_sqrt);
        
        JButton button_delete_all = new JButton("C");
        button_delete_all.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		textField.setText("0");
        	}
        });
        button_delete_all.setFocusPainted(false);
        button_delete_all.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        button_delete_all.setForeground(new Color(255, 255, 255));
        button_delete_all.setBorderPainted(false);
        button_delete_all.setBackground(new Color(59, 59, 59));
        button_delete_all.setBounds(90, 133, 70, 70);
        frame.getContentPane().add(button_delete_all);
        
        JButton button_delete = new JButton("<-");
        button_delete.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		textField.setText(getTextFieldWhenDropData(textField));
        	}
        });
        button_delete.setFocusPainted(false);
        button_delete.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        button_delete.setForeground(new Color(255, 255, 255));
        button_delete.setBorderPainted(false);
        button_delete.setBackground(new Color(59, 59, 59));
        button_delete.setBounds(170, 133, 70, 70);
        frame.getContentPane().add(button_delete);
        
        JButton button_divide = new JButton("/");
        button_divide.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                String text = textField.getText();
                if (text.isEmpty()) {
                    return;
                }

                if (!checkOnlyOneOperator(textField)) {
                    return;
                }

                int lastOperatorIndex = -1;
                for (char operator : "+-*/".toCharArray()) {
                    int index = text.lastIndexOf(operator);
                    if (index > lastOperatorIndex) {
                        lastOperatorIndex = index;
                    }
                }
                
                if (lastOperatorIndex == textField.getText().length() - 1)
                    textField.setText(text.substring(0, lastOperatorIndex) + "/" + text.substring(lastOperatorIndex + 1));
                if (lastOperatorIndex == -1)
                    textField.setText(text + "/");
                
                return;
            }
        });
        button_divide.setFocusPainted(false);
        button_divide.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        button_divide.setForeground(new Color(255, 255, 255));
        button_divide.setBorderPainted(false);
        button_divide.setBackground(new Color(59, 59, 59));
        button_divide.setBounds(250, 133, 70, 70);
        frame.getContentPane().add(button_divide);
      
        JButton button_recent = new JButton("<<<");
        button_recent.setFocusPainted(false);
        button_recent.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        button_recent.setForeground(new Color(255, 255, 255));
        button_recent.setBorderPainted(false);
        button_recent.setBackground(new Color(59, 59, 59));
        button_recent.setBounds(10, 453, 70, 70);
        frame.getContentPane().add(button_recent);
        
        JButton button_close = new JButton("X");
        button_close.setForeground(Color.WHITE);
        button_close.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        button_close.setFocusPainted(false);
        button_close.setBorderPainted(false); 
        button_close.setOpaque(true);
        button_close.setBackground(new Color(0, 0, 0, 0));
        
        JButton button_MC = new JButton("MC");
       	button_MC.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                memoryValue = BigDecimal.ZERO;
            }
        });
       	button_MC.setForeground(Color.WHITE);
       	button_MC.setFont(new Font("Segoe UI", Font.PLAIN, 12));
       	button_MC.setFocusPainted(false);
       	button_MC.setBorderPainted(false);
       	button_MC.setBackground(new Color(59, 59, 59));
       	button_MC.setBounds(10, 71, 53, 51);
       	frame.getContentPane().add(button_MC);
       	
       	JButton button_MS = new JButton("MS");
       	button_MS.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                memoryValue = new BigDecimal(textField.getText());
            }
        });
       	button_MS.setForeground(Color.WHITE);
       	button_MS.setFont(new Font("Segoe UI", Font.PLAIN, 12));
       	button_MS.setFocusPainted(false);
       	button_MS.setBorderPainted(false);
       	button_MS.setBackground(new Color(59, 59, 59));
       	button_MS.setBounds(267, 71, 53, 51);
       	frame.getContentPane().add(button_MS);
       	
       	JButton button_MPLUS = new JButton("M+");
       	button_MPLUS.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                memoryValue = memoryValue.add(new BigDecimal(textField.getText()));
            }
        });
       	button_MPLUS.setForeground(Color.WHITE);
       	button_MPLUS.setFont(new Font("Segoe UI", Font.PLAIN, 12));
       	button_MPLUS.setFocusPainted(false);
       	button_MPLUS.setBorderPainted(false);
       	button_MPLUS.setBackground(new Color(59, 59, 59));
       	button_MPLUS.setBounds(140, 71, 53, 51);
       	frame.getContentPane().add(button_MPLUS);
       	
       	JButton button_MMINUS = new JButton("M-");
       	button_MMINUS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                memoryValue = memoryValue.subtract(new BigDecimal(textField.getText()));
            }
        });
       	button_MMINUS.setForeground(Color.WHITE);
       	button_MMINUS.setFont(new Font("Segoe UI", Font.PLAIN, 12));
       	button_MMINUS.setFocusPainted(false);
       	button_MMINUS.setBorderPainted(false);
       	button_MMINUS.setBackground(new Color(59, 59, 59));
       	button_MMINUS.setBounds(204, 71, 53, 51);
       	frame.getContentPane().add(button_MMINUS);
       	
       	JButton button_MR = new JButton("MR");
       	button_MR.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textField.setText(memoryValue.toString());
            }
        });
       	button_MR.setForeground(Color.WHITE);
       	button_MR.setFont(new Font("Segoe UI", Font.PLAIN, 12));
       	button_MR.setFocusPainted(false);
       	button_MR.setBorderPainted(false);
       	button_MR.setBackground(new Color(59, 59, 59));
       	button_MR.setBounds(73, 71, 53, 51);
       	frame.getContentPane().add(button_MR);
        
        // Array for buttons
        
        List<JButton> buttonArray = new ArrayList<>();
        
       	buttonArray.add(button_0);
       	buttonArray.add(button_1);
       	buttonArray.add(button_2);
       	buttonArray.add(button_3);
       	buttonArray.add(button_4);
       	buttonArray.add(button_5);
       	buttonArray.add(button_6);
       	buttonArray.add(button_7);
       	buttonArray.add(button_8);
       	buttonArray.add(button_9);
       	buttonArray.add(button_delete);
       	buttonArray.add(button_delete_all);
       	buttonArray.add(button_divide);
       	buttonArray.add(button_dot);
       	buttonArray.add(button_equals);
       	buttonArray.add(button_minus);
       	buttonArray.add(button_multiply);
       	buttonArray.add(button_plus);
       	buttonArray.add(button_recent);
       	buttonArray.add(button_sqrt);
       	buttonArray.add(button_MMINUS);
       	buttonArray.add(button_MPLUS);
       	buttonArray.add(button_MR);
       	buttonArray.add(button_MS);
       	buttonArray.add(button_MC);
       	
       	button_recent.addActionListener(new ActionListener() {
       	    public void actionPerformed(ActionEvent e) {
       	        for (JButton button : buttonArray) {
       	            button.setEnabled(false);
       	            button.setVisible(false);
       	        }

       	        recentPanel.setVisible(true);

       	        recentPanel.removeAll();

       	        recentPanel.setLayout(new BoxLayout(recentPanel, BoxLayout.Y_AXIS));

       	        JPanel rightPanel = new JPanel();
       	        rightPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

       	        rightPanel.add(button_close);
       	        rightPanel.setBorder(BorderFactory.createEmptyBorder());
       	        rightPanel.setOpaque(false);
       	        recentPanel.add(rightPanel);

       	        for (String str : recentExamples) {
       	            JLabel lb = new JLabel(str);
       	            lb.setForeground(Color.white);
       	            lb.setOpaque(true);
       	            lb.setBackground(new Color(0, 0, 0, 0));
       	            Font newFont = lb.getFont().deriveFont(lb.getFont().getSize() * 2f);
       	            lb.setFont(newFont);
       	            
       	            lb.addMouseListener(new MouseAdapter() {
       	                public void mouseClicked(MouseEvent e) {
       	                	textField.setText(lb.getText());
       	                	
       	                }
       	            });

       	            
       	            JPanel labelPanel = new JPanel();
       	            labelPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
       	            labelPanel.add(lb);
       	            labelPanel.setOpaque(false);
       	            labelPanel.setBackground(new Color(0, 0, 0, 0));
       	            
       	            recentPanel.add(Box.createRigidArea(new Dimension(0, 10)));
       	            recentPanel.add(labelPanel);
       	        }
       	        
       	        button_close.addActionListener(new ActionListener() {
       	        	public void actionPerformed(ActionEvent e) {
       	        		recentPanel.setVisible(false);
       	        		if (buttonArray.isEmpty())
       	        			return;
       	       	        for (JButton button : buttonArray) {
       	       	            button.setEnabled(true);
       	       	            button.setVisible(true);
       	       	        }
       	        	}
       	        });
       	        
       	    }
       	});
        
        // Binding keys for buttons
        
        button_delete.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_BACK_SPACE, 0), "pressed Backspace");
        button_delete.getActionMap().put("pressed Backspace", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	button_delete.doClick();
            }
        });
        
        button_delete_all.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_BACK_SPACE, InputEvent.CTRL_DOWN_MASK), "pressed Ctrl-Backspace");
        button_delete_all.getActionMap().put("pressed Ctrl-Backspace", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	button_delete_all.doClick();
            }
        });

        button_equals.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "pressed ENTER");
        button_equals.getActionMap().put("pressed ENTER", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	button_equals.doClick();
            }
        });
        	
        button_plus.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ADD, 0), "pressed +");
        button_plus.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("control EQUALS"), "pressed +");
        button_plus.getActionMap().put("pressed +", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                button_plus.doClick();
            }
        });

        button_minus.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_SUBTRACT, 0), "pressed MINUS");
        button_minus.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_MINUS, 0), "pressed MINUS");
        button_minus.getActionMap().put("pressed MINUS", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                button_minus.doClick();
            }
        });

        
        button_divide.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_DIVIDE, 0), "pressed DIVIDE");
        button_divide.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_SLASH, 0), "pressed DIVIDE");
        button_divide.getActionMap().put("pressed DIVIDE", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	button_divide.doClick();
            }
        });
        
        button_multiply.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_MULTIPLY, 0), "pressed MULTIPLY");
        button_multiply.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("shift 8"), "pressed MULTIPLY");
        button_multiply.getActionMap().put("pressed MULTIPLY", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	button_multiply.doClick();
            }
        });
        
        button_dot.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_DECIMAL, 0), "pressed DOT");
        button_dot.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_PERIOD, 0), "pressed DOT");
        button_dot.getActionMap().put("pressed DOT", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                button_dot.doClick();
            }
        });

        
        button_0.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_0, 0), "pressed 0");
        button_0.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD0, 0), "pressed 0");
        button_0.getActionMap().put("pressed 0", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                button_0.doClick();
            }
        });

        
        button_1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_1, 0), "pressed 1");
        button_1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD1, 0), "pressed 1");
        button_1.getActionMap().put("pressed 1", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	button_1.doClick();
            }
        });
        
        button_2.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_2, 0), "pressed 2");
        button_2.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD2, 0), "pressed 2");
        button_2.getActionMap().put("pressed 2", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	button_2.doClick();
            }
        });
        
        button_3.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_3, 0), "pressed 3");
        button_3.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD3, 0), "pressed 3");
        button_3.getActionMap().put("pressed 3", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	button_3.doClick();
            }
        });
        
        button_4.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_4, 0), "pressed 4");
        button_4.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD4, 0), "pressed 4");
        button_4.getActionMap().put("pressed 4", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	button_4.doClick();
            }
        });
        
        button_5.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_5, 0), "pressed 5");
        button_5.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD5, 0), "pressed 5");
        button_5.getActionMap().put("pressed 5", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	button_5.doClick();
            }
        });
        
        button_6.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_6, 0), "pressed 6");
        button_6.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD6, 0), "pressed 6");
        button_6.getActionMap().put("pressed 6", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	button_6.doClick();
            }
        });
        
        button_7.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_7, 0), "pressed 7");
        button_7.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD7, 0), "pressed 7");
        button_7.getActionMap().put("pressed 7", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	button_7.doClick();
            }
        });
        
        button_8.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_8, 0), "pressed 8");
        button_8.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD8, 0), "pressed 8");
        button_8.getActionMap().put("pressed 8", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	button_8.doClick();
            }
        });
        
        button_9.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_9, 0), "pressed 9");
        button_9.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD9, 0), "pressed 9");
        button_9.getActionMap().put("pressed 9", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	button_9.doClick();
            }
        });
        
        //
        
        textField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                adjustFontSize();
                checkMaxLength();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                adjustFontSize();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }

            private void checkMaxLength() {
            	if (textField.getText().length() > 29) {
            		String newText = textField.getText().substring(0, 29);
                    textField.setText(newText);
            	}
            }
            
            private void adjustFontSize() {
                if (textField.getText().length() > 12) {
                    if (fontSize > 18) {
                    	fontSize = fontSize - 1;
                        textField.setFont(new Font("Segou UI", Font.PLAIN, fontSize));
                    }
                }
                
                if (textField.getText().length() <= 12 && textField.getText().length() > 1) {
                	fontSize = 40;
                	textField.setFont(new Font("Segou UI", Font.PLAIN, fontSize));
                }
            	
            	if (textField.getText() == "0" || textField.getText().length() == 1) {
            		fontSize = 40;
            		textField.setFont(new Font("Segou UI", Font.PLAIN, fontSize));
            	}
            }
        });
        
        
        frame.setResizable(false);
        frame.setVisible(true);
    }
    
    private static String getTextFieldWhenDropData(JTextField tf) {
    	if (tf.getText().length() < 2)
    		return "0";
    	return tf.getText().substring(0, tf.getText().length() - 1);
    }
    
    private static String dropZeroFromTextField(JTextField tf) {
    	if ("0".equals(tf.getText())) {
    		return "";
    	}
    	return tf.getText();
    }
    
    private static boolean checkOnlyOneOperator(JTextField tf) {
        String text = tf.getText();
        if (text.isEmpty()) {
            return true;
        }

        char[] charText = text.toCharArray();
        if (charText[0] == '-') {
            text = text.substring(1);
        }

        int operatorCount = 0;
        for (char ch : "+-*/".toCharArray()) {
            operatorCount += countChar(text, ch);
            if (operatorCount > 1) {
                return false;
            }
        }

        return true;
    }

    private static int countChar(String str, char ch) {
        return (int) str.chars().filter(c -> c == ch).count();
    }

    private static BigDecimal evaluateExpression(String expression) {
        char[] tokens = expression.toCharArray();

        int currentIndex = 0;

        BigDecimal firstOperand = getOperand(tokens, currentIndex);
        currentIndex += getOperandLength(tokens, currentIndex);

        char operator = tokens[currentIndex++];

        BigDecimal secondOperand = getOperand(tokens, currentIndex);

        switch (operator) {
            case '+':
                return firstOperand.add(secondOperand);
            case '-':
                return firstOperand.subtract(secondOperand);
            case '*':
                return firstOperand.multiply(secondOperand);
            case '/':
                if (secondOperand.equals(BigDecimal.ZERO)) {
                    return new BigDecimal("0");
                }
                return firstOperand.divide(secondOperand, 8, BigDecimal.ROUND_HALF_UP).stripTrailingZeros();
            default:
                throw new RuntimeException("Неподдерживаемая операция: " + operator);
        }
    }

    private static BigDecimal getOperand(char[] tokens, int startIndex) {
        StringBuilder operandBuilder = new StringBuilder();

        if (startIndex < tokens.length && tokens[startIndex] == '-') {
            operandBuilder.append('-');
            startIndex++;
        }

        while (startIndex < tokens.length &&
                (Character.isDigit(tokens[startIndex]) || tokens[startIndex] == '.')) {
            operandBuilder.append(tokens[startIndex]);
            startIndex++;
        }

        return new BigDecimal(operandBuilder.toString());
    }

    private static int getOperandLength(char[] tokens, int startIndex) {
        int length = 0;

        boolean hasMinus = false;
        if (startIndex < tokens.length && tokens[startIndex] == '-') {
            hasMinus = true;
            startIndex++;
        }

        while (startIndex + length < tokens.length &&
                (Character.isDigit(tokens[startIndex + length]) || tokens[startIndex + length] == '.')) {
            length++;
        }

        if (hasMinus) {
            length++;
        }

        return length;
    }

    
    private static boolean checkOnlyOneDotInOperand(String expression) {
        char[] tokens = expression.toCharArray();

        boolean numHasDot = false;
        
        for (int i = 0; i < tokens.length; i++) {
            char ch = tokens[i];

            if (ch == '.') {
                numHasDot = true;
            }
            
            if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
                numHasDot = false;
            }        
        }
        
    	if (tokens[tokens.length - 1] == '+' || tokens[tokens.length - 1] == '*' || tokens[tokens.length - 1] == '/') {
    		return false;
    	}
        
        if (numHasDot) {
            return false;
        }
        
        return true;
    }
    
    private static void addElementInList(List<String> List, String line) {
    	if (List.size() > 4) {
    		List.remove(List.size() - 1);
    	}
    	
    	List.add(0, line);
    }
}
