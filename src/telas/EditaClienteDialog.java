/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package telas;

import despachantefacil.models.Cliente;
import despachantefacil.models.Endereco;
import despachantefacil.models.dao.ClienteDAO;
import despachantefacil.models.enums.SexoENUM;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

/**
 *
 * @author vinic
 */
public class EditaClienteDialog extends javax.swing.JFrame {
    private Cliente cliente;
    
    
    public EditaClienteDialog(int idCliente) {
    super("Editar Cliente");
    initComponents(); 
    preencherJComboBox1();
    

    //conexão com o banco através do ClienteDAO para buscar as informações do cliente através do idCliente
    ClienteDAO clienteDAO = new ClienteDAO();
    this.cliente = clienteDAO.getClienteById(idCliente);

    if (this.cliente == null) {
        System.out.println("Erro: Cliente com id " + idCliente + " não foi encontrado no banco de dados.");
        return;
    }

    //preenchendo as informações da tela com os dados do banco de dados
    this.txtNome.setText(cliente.getNome());
    this.txtContato1.setText(cliente.getContato1());
    this.txtContato2.setText(cliente.getContato2());
    this.txtCpf.setText(cliente.getCpf());
    this.txtObservacoes.setText(cliente.getObservacoes());
    this.txtFilePath.setText(cliente.getFilePath());
    this.txtFilePath.setEditable(false);//para evitar edição manual do usuário no campo de texto de digitação de CNH
    

    if(cliente.getSexoENUM() != null) {
        this.jComboBox1.setSelectedItem(cliente.getSexoENUM().toString());
    } else {
        System.out.println("Erro: Cliente com id " + idCliente + " não tem um SexoENUM definido.");
    }

    if(cliente.getEndereco() != null) {
        this.txtBairro.setText(cliente.getEndereco().getBairro());
        this.txtCep.setText(cliente.getEndereco().getCep());
        this.txtCidade.setText(cliente.getEndereco().getCidade());
        this.txtEstado.setText(cliente.getEndereco().getUf());
        this.txtLogradouro.setText(cliente.getEndereco().getLogradouro());
        this.txtNumero.setText(String.valueOf(cliente.getEndereco().getNumero()));
    } else {
        System.out.println("Erro: Cliente com id " + idCliente + " não tem um endereço associado.");
    }
    
    //configuração imagem
    String filePath = cliente.getFilePath();
    if(filePath != null && !filePath.isEmpty()){
        ImageIcon imageIcon = new ImageIcon(filePath);
        Image image = imageIcon.getImage(); // transformá-la
        Image newimg = image.getScaledInstance(jLabelImage.getWidth(), jLabelImage.getHeight(),  java.awt.Image.SCALE_SMOOTH); // redimensioná-la aqui
        imageIcon = new ImageIcon(newimg);  // transformá-la novamente
        jLabelImage.setIcon(imageIcon);
    }
    
    displayImage(); //inserção da opção de visualização da imagem com um clique e botão de impressão
    
    
}
    

//colocando os sexos no combo box
    private void preencherJComboBox1(){
        jComboBox1.removeAllItems();
        SexoENUM[] sexos = SexoENUM.values();
        for (SexoENUM sexo : sexos){
            jComboBox1.addItem(sexo.name());
        }
        
    }
    


    //botão de impressão
    private void displayImage() {
        //criação de MouseListener possibilitando abrir a imagem com um clique do mouse
    jLabelImage.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            //verificação se há imagem
            if (jLabelImage.getIcon() != null) {
                ImageIcon icon = (ImageIcon) jLabelImage.getIcon();
                JDialog dialog = new JDialog();
                dialog.setLayout(new BorderLayout());

                //criação de novo JLabel para mostrar a imagem
                JLabel label = new JLabel();
                label.setIcon(icon);
                JScrollPane scrollPane = new JScrollPane(label);
                dialog.add(scrollPane, BorderLayout.CENTER);

                //adição de botão de impressão
                JButton printButton = new JButton("Imprimir");
                printButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        PrinterJob job = PrinterJob.getPrinterJob();
                        job.setPrintable(new Printable() {
                            @Override
                            public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
                                if (pageIndex != 0) {
                                    return NO_SUCH_PAGE;
                                }
                                Graphics2D g2 = (Graphics2D) graphics;
                                g2.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
                                g2.drawImage(icon.getImage(), 0, 0, label);
                                return PAGE_EXISTS;
                            }
                        });
                        boolean ok = job.printDialog();
                        if (ok) {
                            try {
                                job.print();
                            } catch (PrinterException ex) {
                                
                            }
                        }
                    }
                });
                dialog.add(printButton, BorderLayout.SOUTH);
                
                dialog.pack();
                dialog.setLocationRelativeTo(null); // Centralize a janela de diálogo
                dialog.setVisible(true);
            }
        }
    });
}

   //método não utilizado
   /*
    public void preencherCampos(Cliente cliente) {
    this.cliente = cliente;  //salvando o cliente para uso posterior

    // Preenchendo os campos com os detalhes do cliente
    txtNome.setText(cliente.getNome());
    txtCpf.setText(cliente.getCpf());
    txtContato1.setText(cliente.getContato1());
    txtContato2.setText(cliente.getContato2());
    txtObservacoes.setText(cliente.getObservacoes());
    jComboBox1.setSelectedItem(cliente.getSexoENUM().name());
    txtFilePath.setText(cliente.getFilePath());

    // Preenchendo os campos com os detalhes do endereço do cliente
    txtLogradouro.setText(cliente.getEndereco().getLogradouro());
    txtNumero.setText(String.valueOf(cliente.getEndereco().getNumero()));
    txtBairro.setText(cliente.getEndereco().getBairro());
    txtCidade.setText(cliente.getEndereco().getCidade());
    txtEstado.setText(cliente.getEndereco().getUf());
    txtCep.setText(cliente.getEndereco().getCep());

}
    */
    
    public void atualizarCliente() {
    //atualizando o endereço vinculado ao cliente
    Endereco endereco = cliente.getEndereco();
    endereco.setLogradouro(txtLogradouro.getText());
    endereco.setNumero(Integer.parseInt(txtNumero.getText()));
    endereco.setBairro(txtBairro.getText());
    endereco.setCidade(txtCidade.getText());
    endereco.setUf(txtEstado.getText());
    endereco.setCep(txtCep.getText());

    //atualizando o cliente
    cliente.setNome(txtNome.getText());
    cliente.setCpf(txtCpf.getText());
    cliente.setSexoENUM(SexoENUM.valueOf((String) jComboBox1.getSelectedItem()));
    cliente.setContato1(txtContato1.getText());
    cliente.setContato2(txtContato2.getText());
    cliente.setObservacoes(txtObservacoes.getText());
    cliente.setEndereco(endereco);
    
    //salvando o texto txtFilePath que foi introduzido após o usuário utilizar o botão para acessar o diretório do arquivo
    String filePath = txtFilePath.getText();
    cliente.setFilePath(filePath);

    //salvando as alterações no banco de dados através do ClienteDAO.
    ClienteDAO clienteDAO = new ClienteDAO();
    clienteDAO.updateCliente(cliente);
}



    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        txtCpf = new javax.swing.JTextField();
        txtLogradouro = new javax.swing.JTextField();
        txtNumero = new javax.swing.JTextField();
        txtBairro = new javax.swing.JTextField();
        txtCidade = new javax.swing.JTextField();
        txtEstado = new javax.swing.JTextField();
        txtContato1 = new javax.swing.JTextField();
        txtContato2 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtObservacoes = new javax.swing.JTextArea();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        txtFilePath = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtCep = new javax.swing.JTextField();
        jLabelImage = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Cadastro de Cliente");
        jLabel1.setToolTipText("");

        jLabel2.setText("Nome");

        jLabel3.setText("CPF");

        jLabel4.setText("Sexo");

        jLabel5.setText("Logradouro");

        jLabel6.setText("Número");

        jLabel7.setText("Bairro");

        jLabel8.setText("Cidade");

        jLabel9.setText("Estado");

        jLabel10.setText("Telefone");

        jLabel11.setText("Telefone 2");

        jLabel12.setText("Observacoes");

        txtObservacoes.setColumns(20);
        txtObservacoes.setRows(5);
        jScrollPane1.setViewportView(txtObservacoes);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jButton1.setText("Salvar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Voltar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Digitalizar Documento");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        txtFilePath.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFilePathActionPerformed(evt);
            }
        });

        jLabel13.setText("CEP");

        jLabelImage.setText("Imagem");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(320, 320, 320)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel12)
                                .addComponent(jLabel11)
                                .addComponent(jLabel10)
                                .addComponent(jLabel9)
                                .addComponent(jLabel8)
                                .addComponent(jLabel7)))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(96, 96, 96)
                            .addComponent(jButton2)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(jLabelImage, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel13)
                        .addGap(18, 18, 18)
                        .addComponent(txtCep))
                    .addComponent(jScrollPane1)
                    .addComponent(txtNome)
                    .addComponent(txtCpf)
                    .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtLogradouro)
                    .addComponent(txtBairro)
                    .addComponent(txtCidade)
                    .addComponent(txtEstado)
                    .addComponent(txtContato1)
                    .addComponent(txtContato2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 159, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(154, 154, 154))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(344, 344, 344))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtFilePath)
                            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(434, 434, 434))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabelImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(txtCpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel5)
                                    .addComponent(txtLogradouro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel6)
                                    .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel13)
                                    .addComponent(txtCep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7)
                                    .addComponent(txtBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel8)
                                    .addComponent(txtCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel9)
                                    .addComponent(txtEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel10)
                                    .addComponent(txtContato1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel11)
                                    .addComponent(txtContato2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addGap(18, 18, 18)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtFilePath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        atualizarCliente();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
        
        if (jComboBox1.getSelectedItem() != null) {
            String selectedItem = jComboBox1.getSelectedItem().toString();
            SexoENUM sexo = SexoENUM.valueOf(selectedItem);
        }
        
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        
        //comando para acessar os arquivos do Windows e escolher a foto que será armazenada
        JFileChooser fileChooser = new JFileChooser();
    int returnValue = fileChooser.showOpenDialog(null);
    if (returnValue == JFileChooser.APPROVE_OPTION) {
        File selectedFile = fileChooser.getSelectedFile();
        Path destinationPath = Paths.get("C:\\Users\\vinic\\Documents\\DespachanteFacilFotos", selectedFile.getName()); //copiando a foto selecionada para um diretório do sistema
        try {
            Files.copy(selectedFile.toPath(), destinationPath, StandardCopyOption.REPLACE_EXISTING);
            String filePath = destinationPath.toString(); //armazenando o local do arquivo para o txtFilePath para que futuramente seja salva no banco de dados
            txtFilePath.setText(filePath);
        } catch (IOException ex) {
            ex.printStackTrace();
            txtFilePath.setText("erro");
            }

    }
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        ListaClientes telaListaClientes = new ListaClientes();
        telaListaClientes.setVisible(true);
        this.dispose();  // Este comando vai fechar a tela atual
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txtFilePathActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFilePathActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFilePathActionPerformed

    /**
     * @param args the command line arguments
     */
 

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelImage;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtBairro;
    private javax.swing.JTextField txtCep;
    private javax.swing.JTextField txtCidade;
    private javax.swing.JTextField txtContato1;
    private javax.swing.JTextField txtContato2;
    private javax.swing.JTextField txtCpf;
    private javax.swing.JTextField txtEstado;
    private javax.swing.JTextField txtFilePath;
    private javax.swing.JTextField txtLogradouro;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtNumero;
    private javax.swing.JTextArea txtObservacoes;
    // End of variables declaration//GEN-END:variables
}
