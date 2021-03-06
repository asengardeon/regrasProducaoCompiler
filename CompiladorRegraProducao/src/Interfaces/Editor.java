package Interfaces;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.RenderingHints.Key;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.BevelBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import net.miginfocom.swing.MigLayout;
import java.awt.Dimension;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.ImageIcon;

import compiler.LexicalError;
import compiler.Lexico;
import compiler.SemanticError;
import compiler.Semantico;
import compiler.Sintatico;
import compiler.SyntaticError;

import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Editor extends JFrame {

	private JPanel pContainer;
	private JLabel lChange;
	private JLabel lDirectory;
	private JTextArea tAMessages;
	private LineNumbering tASources;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Editor frame = new Editor();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Editor() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 785, 541);

		pContainer = new JPanel();
		pContainer.setMinimumSize(new Dimension(93, 24));
		pContainer.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(pContainer);

		JPanel pButtons = new JPanel();
		pButtons.setPreferredSize(new Dimension(93, 24));
		pButtons.setMinimumSize(new Dimension(93, 24));

		JPanel pSatusBar = new JPanel();

		JPanel pContainerEditor = new JPanel();
		GroupLayout gl_pContainer = new GroupLayout(pContainer);
		gl_pContainer.setHorizontalGroup(gl_pContainer
				.createParallelGroup(Alignment.LEADING)
				.addGroup(
						gl_pContainer
								.createSequentialGroup()
								.addComponent(pButtons,
										GroupLayout.PREFERRED_SIZE, 158,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(pContainerEditor,
										GroupLayout.DEFAULT_SIZE, 627,
										Short.MAX_VALUE))
				.addComponent(pSatusBar, GroupLayout.DEFAULT_SIZE,
						GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		gl_pContainer.setVerticalGroup(gl_pContainer.createParallelGroup(
				Alignment.TRAILING).addGroup(
				gl_pContainer
						.createSequentialGroup()
						.addGroup(
								gl_pContainer
										.createParallelGroup(Alignment.LEADING)
										.addComponent(pButtons,
												GroupLayout.DEFAULT_SIZE, 425,
												Short.MAX_VALUE)
										.addComponent(pContainerEditor,
												Alignment.TRAILING,
												GroupLayout.DEFAULT_SIZE, 425,
												Short.MAX_VALUE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(pSatusBar, GroupLayout.PREFERRED_SIZE,
								19, GroupLayout.PREFERRED_SIZE)));

		lChange = new JLabel("");
		lChange.setBorder(UIManager.getBorder("ProgressBar.border"));

		lDirectory = new JLabel("");
		lDirectory.setBorder(UIManager.getBorder("ProgressBar.border"));
		GroupLayout gl_pSatusBar = new GroupLayout(pSatusBar);
		gl_pSatusBar.setHorizontalGroup(gl_pSatusBar.createParallelGroup(
				Alignment.LEADING).addGroup(
				gl_pSatusBar
						.createSequentialGroup()
						.addComponent(lChange, GroupLayout.PREFERRED_SIZE, 120,
								GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(lDirectory, GroupLayout.DEFAULT_SIZE,
								632, Short.MAX_VALUE).addGap(1)));
		gl_pSatusBar.setVerticalGroup(gl_pSatusBar
				.createParallelGroup(Alignment.LEADING)
				.addComponent(lChange, GroupLayout.DEFAULT_SIZE, 19,
						Short.MAX_VALUE)
				.addComponent(lDirectory, GroupLayout.DEFAULT_SIZE, 19,
						Short.MAX_VALUE));
		pSatusBar.setLayout(gl_pSatusBar);
		pContainerEditor.setLayout(new MigLayout("", "[610px,grow]",
				"[337.00px,grow][100px:100px:200px,fill]"));

		tASources = new LineNumbering();
		pContainerEditor.add(tASources, "cell 0 0,grow");

		JScrollPane aPMessages = new JScrollPane();
		aPMessages.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null,
				null, null));
		aPMessages
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		aPMessages
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		pContainerEditor.add(aPMessages, "cell 0 1,grow");

		tAMessages = new JTextArea();
		// tAMessages.setEnabled(false);
		aPMessages.setViewportView(tAMessages);
		pButtons.setLayout(new GridLayout(9, 0, 0, 0));

		JButton btnNew = new JButton("novo [crt-n]");
		btnNew.setIcon(new ImageIcon(Editor.class.getResource("/Icons/new.png")));
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newSource();
			}
		});
		pButtons.add(btnNew);

		JButton btnOpen = new JButton("abrir [crt-a]");
		btnOpen.setIcon(new ImageIcon(Editor.class
				.getResource("/Icons/open.png")));
		btnOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openSource();
			}
		});
		pButtons.add(btnOpen);

		JButton btnSave = new JButton("salvar [crt-s]");
		btnSave.setIcon(new ImageIcon(Editor.class
				.getResource("/Icons/save.png")));
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveSource();
			}
		});
		pButtons.add(btnSave);

		JButton btnCopy = new JButton("copiar [crt-c]");
		btnCopy.setIcon(new ImageIcon(Editor.class
				.getResource("/Icons/copy.png")));
		btnCopy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				copy();
			}
		});
		pButtons.add(btnCopy);

		JButton btnPaste = new JButton("colar [crt-v]");
		btnPaste.setIcon(new ImageIcon(Editor.class
				.getResource("/Icons/paste.png")));
		btnPaste.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				paste();
			}
		});
		pButtons.add(btnPaste);

		JButton btnCut = new JButton("recortar [crt-x]");
		btnCut.setIcon(new ImageIcon(Editor.class.getResource("/Icons/cut.png")));
		btnCut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cut();
			}
		});
		pButtons.add(btnCut);

		JButton btnCompile = new JButton("compilar [F8]");
		btnCompile.setIcon(new ImageIcon(Editor.class
				.getResource("/Icons/compile.png")));
		btnCompile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				compile();
			}
		});
		pButtons.add(btnCompile);

		// JButton btnGenerateSource = new JButton("gerar c\u00F3digo [F9]");
		JButton btnGenerateSource = new JButton("Executar [F9]");
		btnGenerateSource.setIcon(new ImageIcon(Editor.class
				.getResource("/Icons/generate.png")));
		btnGenerateSource.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				generateSource();
			}
		});
		pButtons.add(btnGenerateSource);

		JButton btnTeam = new JButton("equipe [F1]");
		btnTeam.setIcon(new ImageIcon(Editor.class
				.getResource("/Icons/team.png")));
		btnTeam.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				team();
			}
		});
		pButtons.add(btnTeam);
		pContainer.setLayout(gl_pContainer);

		tASources
				.getEditor()
				.getInputMap()
				.put(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_MASK),
						"new");
		tASources.getEditor().getActionMap()
				.put("new", new AbstractAction("new") {
					@Override
					public void actionPerformed(ActionEvent evt) {
						newSource();
					}
				});

		tASources
				.getEditor()
				.getInputMap()
				.put(KeyStroke.getKeyStroke(KeyEvent.VK_A, KeyEvent.CTRL_MASK),
						"open");
		tASources.getEditor().getActionMap()
				.put("open", new AbstractAction("open") {
					@Override
					public void actionPerformed(ActionEvent evt) {
						openSource();
					}
				});

		tASources
				.getEditor()
				.getInputMap()
				.put(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_MASK),
						"save");
		tASources.getEditor().getActionMap()
				.put("save", new AbstractAction("save") {
					@Override
					public void actionPerformed(ActionEvent evt) {
						saveSource();
					}
				});

		tASources.getEditor().getInputMap()
				.put(KeyStroke.getKeyStroke(KeyEvent.VK_F8, 0), "compile");
		tASources.getEditor().getActionMap()
				.put("compile", new AbstractAction("compile") {
					@Override
					public void actionPerformed(ActionEvent evt) {
						compile();
					}
				});

		tASources.getEditor().getInputMap()
				.put(KeyStroke.getKeyStroke(KeyEvent.VK_F9, 0), "generate");
		tASources.getEditor().getActionMap()
				.put("generate", new AbstractAction("generate") {
					@Override
					public void actionPerformed(ActionEvent evt) {
						generateSource();
					}
				});

		tASources.getEditor().getInputMap()
				.put(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0), "team");
		tASources.getEditor().getActionMap()
				.put("team", new AbstractAction("team") {
					@Override
					public void actionPerformed(ActionEvent evt) {
						team();
					}
				});

		newSource();
		setEditorFocus();
	}

	public void setEditorFocus() {
		tASources.getEditor().setFocusable(true);
	}

	public void newSource() {
		tAMessages.setText("");
		lChange.setText("N�o modificado");
		lDirectory.setText("");
		setEditorFocus();
	}

	public void openSource() {
		JFileChooser searchFile = new JFileChooser();
		searchFile.setApproveButtonText("Selecionar");
		searchFile.setFileSelectionMode(JFileChooser.FILES_ONLY);
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"Arquivos para compila��o", "txt", "txt");
		searchFile.setFileFilter(filter);
		searchFile.setAcceptAllFileFilterUsed(false);

		int returnVal = searchFile.showOpenDialog(this);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			Source source = new Source();
			String patch = searchFile.getSelectedFile().getAbsolutePath();
			try {
				String file = source.open(patch);
				tASources.getEditor().setText(file);
				tAMessages.setText("");
				lDirectory.setText(patch);
				lChange.setText("N�o modificado");
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null,
						"N�o foi poss�vel abrir o arquivo: " + patch);
			}
		}
		setEditorFocus();
	}

	public void saveSource() {
		JFileChooser searchDir = new JFileChooser();
		searchDir
				.setApproveButtonText("Selecione um diret�rio para salvar o arquivo");
		searchDir.setFileSelectionMode(JFileChooser.FILES_ONLY);
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"Arquivos para compila��o", "txt", "txt");
		searchDir.setFileFilter(filter);
		searchDir.setAcceptAllFileFilterUsed(false);

		int returnVal = searchDir.showSaveDialog(this);

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			Source source = new Source();
			String patch = searchDir.getSelectedFile().getAbsolutePath();
			try {
				source.save(patch, tASources.getEditor().getText());
				lDirectory.setText(patch);
				lChange.setText("N�o modificado");
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null,
						"N�o foi salvar o arquivo: " + patch);
			}
		}
		setEditorFocus();
	}

	public void copy() {
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		String text = tASources.getEditor().getSelectedText();
		StringSelection selection = new StringSelection(text);
		clipboard.setContents(selection, null);
		setEditorFocus();
	}

	public void cut() {
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		String text = tASources.getEditor().getSelectedText();
		StringSelection selection = new StringSelection(text);
		clipboard.setContents(selection, null);

		String selText = tASources.getEditor().getSelectedText();
		String source = tASources.getEditor().getText();

		int pos = tASources.getEditor().getCaretPosition();
		String newString = source.substring(0, pos)
				+ source.substring(pos + selText.length(), source.length());
		tASources.getEditor().setText(newString);
		setEditorFocus();

	}

	public void paste() {
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		Transferable contents = clipboard.getContents(null);
		boolean hasTransferableText = (contents != null)
				&& contents.isDataFlavorSupported(DataFlavor.stringFlavor);

		if (hasTransferableText) {
			try {

				int pos = tASources.getEditor().getCaretPosition();
				String source = tASources.getEditor().getText();
				String preText = source.substring(0, pos);
				String newString = preText
						+ (String) contents
								.getTransferData(DataFlavor.stringFlavor)
						+ source.substring(pos, source.length());
				tASources.getEditor().setText(newString);

			} catch (UnsupportedFlavorException ex) {
				System.out.println(ex);
				ex.printStackTrace();
			} catch (IOException ex) {
				System.out.println(ex);
				ex.printStackTrace();
			}
		}
		setEditorFocus();
	}

	public void compile() {
		String source = tASources.getEditor().getText().replaceAll("\r", "")
				+ "\n";

		setEditorFocus();
		Sintatico sintatico = new Sintatico();
		Semantico semantico = new Semantico();
		Lexico lexico = new Lexico();
		lexico.setInput(new StringReader(source));
		try {
			try {
				sintatico.parse(lexico, semantico);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				tAMessages.setText(e.getMessage());
			}
		} catch (LexicalError e) {
			// TODO Auto-generated catch block
			tAMessages.setText(e.getMessage());
		} catch (SyntaticError e) {
			// TODO Auto-generated catch block
			tAMessages.setText(e.getMessage());
		} catch (SemanticError e) {
			// TODO Auto-generated catch block
			tAMessages.setText(e.getMessage());
		}
	}

	public void generateSource() {
		String texto = "";
		texto = "Execu��o ainda n�o implementada\n";
		texto += "\nABAIXO LISTA DE VARI�VEIS INFORMADAS NO PROGRAMA E SEUS VALORES POSS�VEIS";
		
		File arquivo = new File("c:\\variaveis.txt");
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(arquivo);
		} catch (FileNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		int ln;
		
		try {
			while ((ln = fis.read()) != -1) {
				texto += (char) ln;
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		tAMessages.setText(texto);

		try {
			fis.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		setEditorFocus();
	}

	public void team() {
		tAMessages.setText("Equipe:\nLeandro Vilson Battisti; @copyright 2013");
		setEditorFocus();
	}
}
