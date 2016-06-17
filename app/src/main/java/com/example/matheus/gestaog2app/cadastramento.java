package com.example.matheus.gestaog2app;

import android.Manifest;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import DAO.ClienteDAO;
import MODEL.Cliente;
import email.Mail;

public class cadastramento extends AppCompatActivity {

    Context context;
    public static final int RETURNCODE = 200;
    private int id;
    private boolean alterado = false;

    Cliente clienteObjeto = new Cliente();
    ClienteDAO clienteDao = new ClienteDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastramento);

        Spinner servico = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.servicos, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        servico.setAdapter(adapter);


        //context = this;


        if (this.getIntent().hasExtra("ID")) {
            alterado = true;
            this.id = Integer.parseInt(this.getIntent().getStringExtra("ID"));

            this.mostraCampos(id);
        } else {
            alterado = false;
        }



        Button btnSalvar = (Button) findViewById(R.id.btnCadastro);
        Button btnApagar = (Button) findViewById(R.id.btnApagar);

        if (alterado) {
            if (btnApagar != null) {
                btnApagar.setVisibility(View.VISIBLE);
            }
        } else {
            if (btnApagar != null) {
                btnApagar.setVisibility(View.INVISIBLE);
            }
        }


        if (btnSalvar != null) {
            btnSalvar.setOnClickListener(new Button.OnClickListener() {
                @Override
                public void onClick(View v) {

                    EditText nome = (EditText) findViewById(R.id.NomeLabel);
                    EditText telefone = (EditText) findViewById(R.id.TelefoneLabel);
                    EditText cpf = (EditText) findViewById(R.id.CpfLabel);
                    EditText data = (EditText) findViewById(R.id.DataLabel);
                    EditText hora = (EditText) findViewById(R.id.HoraLabel);
                    EditText email = (EditText) findViewById(R.id.EmailLabel);
                    Spinner servico = (Spinner) findViewById(R.id.spinner);



                    if (!campos(nome, telefone, cpf, data, hora, email)) {

                        if (alterado){
                            clienteObjeto.setId(id);
                        }

                        clienteObjeto.setNome(nome.getText().toString());
                        clienteObjeto.setTelefone(telefone.getText().toString());
                        clienteObjeto.setCpf(cpf.getText().toString());
                        clienteObjeto.setEmail(email.getText().toString());
                        clienteObjeto.setServico(servico.getSelectedItem().toString());
                        clienteObjeto.setHorario(hora.getText().toString());
                        clienteObjeto.setDia(data.getText().toString());


                        clienteDao.salvar(clienteObjeto);




                        String SENT = "SMS_SENT";

                        PendingIntent sentPI = PendingIntent.getBroadcast(getApplicationContext(), 0, new Intent(SENT), 0);

                        SmsManager smsManager = SmsManager.getDefault();
                        smsManager.sendTextMessage("5191039301", null, "Ola Mundo", sentPI, null);


                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            if (checkSelfPermission(Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
                                requestPermissions(new String[]{Manifest.permission.SEND_SMS}, 10061);
                            }
                        }


                        Mail mail = new Mail();
                        String[] toArr = {"jonas.costa1987@gmail.com"};
                        mail.set_to(toArr);
                        mail.setBody("Voce tem um cliente novo agendado para: " + clienteObjeto.getHorario()+
                        " No dia: " + clienteObjeto.getDia() + "\n O Cliente: " + clienteObjeto.getNome() + " Agendou o Servico de " + clienteObjeto.getServico());

                        try {

                            if(mail.send()){
                                Toast toast = Toast.makeText(getApplicationContext(), "Email enviado", Toast.LENGTH_SHORT);
                                toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL, 0, 0);
                                toast.show();
                            }else{
                                Toast toast = Toast.makeText(getApplicationContext(), "Falha ao enviar email", Toast.LENGTH_SHORT);
                                toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL, 0, 0);
                                toast.show();
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        clienteDao.lista();






                        finish();
                    }
                }

                    //verifica se os campos estao vazios
                private boolean campos(EditText... fields) {
                    boolean empty = false;

                    for (EditText field : fields) {

                        if (TextUtils.isEmpty(field.getText().toString())) {
                            empty = true;
                            field.setError("campo deve ser preexido");
                        }

                    }

                    return empty;
                }

            });


            if (btnApagar != null) {
                btnApagar.setOnClickListener(new Button.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        clienteObjeto.setId(id);
                        clienteDao.deletar(clienteObjeto);
                        clienteDao.lista();
                        finish();

                    }
                });
            }


        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);


        menu.add(0, 1, 0, "Tela Login");
        menu.add(0, 2, 0, "Menu Inicial");
        menu.add(0, 3, 0, "Consulta");

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch(item.getItemId()){

            case 1:
                Intent j = new Intent(this, Login.class);
                startActivityForResult(j, RETURNCODE);
                return true;

            case 2:
                Intent k = new Intent(this, Navigation.class);
                startActivityForResult(k, RETURNCODE);
                return true;

            case 3:
                Intent l = new Intent(this, consultas.class);
                startActivityForResult(l, RETURNCODE);
                return true;
        }

        return false;
    }

    private void mostraCampos(int id) {

        EditText nome =  (EditText) findViewById(R.id.NomeLabel);
        nome.setText(this.getIntent().getStringExtra("NOME"));

        EditText telefone = (EditText) findViewById(R.id.TelefoneLabel);
        telefone.setText(this.getIntent().getStringExtra("TELEFONE"));

        EditText cpf = (EditText) findViewById(R.id.CpfLabel);
        cpf.setText(this.getIntent().getStringExtra("CPF"));

        EditText data = (EditText) findViewById(R.id.DataLabel);
        data.setText(this.getIntent().getStringExtra("DATA"));

        EditText hora = (EditText) findViewById(R.id.HoraLabel);
        hora.setText(this.getIntent().getStringExtra("HORA"));

        EditText email = (EditText) findViewById(R.id.EmailLabel);
        email.setText(this.getIntent().getStringExtra("EMAIL"));

        Spinner servico = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> str = (ArrayAdapter<String>) servico.getAdapter();
        servico.setSelection(str.getPosition(this.getIntent().getStringExtra("SERVICO")));


    }







}

