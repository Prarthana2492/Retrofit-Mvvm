package com.FarmPe.Oxkart.Activity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.FarmPe.Oxkart.R;

import java.util.function.Consumer;

public class Async_task_Activity extends AppCompatActivity {

    private Button button;
    private EditText time;
    private TextView finalResult;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.async_task_layout);


        time = (EditText) findViewById(R.id.in_time);
        button = (Button) findViewById(R.id.btn_run);
        finalResult = (TextView) findViewById(R.id.tv_result);


        button.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
//                AsyncTaskRunner runner = new AsyncTaskRunner();// object
//                String sleepTime = time.getText().toString();
//                runner.execute(sleepTime);


                Thread producer = new Producer();
                Thread consumer = new Consumer();

             //   producer.setPriority(Thread.MIN_PRIORITY); //Min Priority
               // consumer.setPriority(Thread.MAX_PRIORITY); //Max Priority

                producer.start();
                consumer.start();
            }
        });
    }





    class AsyncTaskRunner extends AsyncTask<String, String, String> {

        private String resp;
        ProgressDialog progressDialog;

        @Override
        protected String doInBackground(String... params) {
            publishProgress("Sleeping..."); // Calls onProgressUpdate()
            try {
                int time = Integer.parseInt(params[0])*1000;

                Thread.sleep(time);
                resp = "Slept for " + params[0] + " seconds";

            } catch (InterruptedException e) {
                e.printStackTrace();
                resp = e.getMessage();

            } catch (Exception e) {
                e.printStackTrace();
                resp = e.getMessage();
            }
            return resp;
        }


        @Override
        protected void onPostExecute(String result) {
            // execution of result of Long time consuming operation
            progressDialog.dismiss();
           finalResult.setText(result);
        }


        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(Async_task_Activity.this,
                    "ProgressDialog","Wait for "+time.getText().toString()+ " seconds");

        }


        @Override
        protected void onProgressUpdate(String... text) {
           finalResult.setText(text[0]);

        }
    }

    class Producer extends Thread
    {
        public void run()
        {
            for (int i = 0; i < 3; i++)
            {
                System.out.println("Producer : Produced Item " + i);
               Thread.yield();
            }
        }
    }
    /*Threads---Thread2*/

    class Consumer extends Thread {
        public void run() {
            for (int i = 0; i < 3; i++) {
                System.out.println("Consumer : Consumed Item " + i);
                //Thread.yield();
            }
        }

    }


}

