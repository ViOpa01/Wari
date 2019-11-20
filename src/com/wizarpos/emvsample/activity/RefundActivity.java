package com.wizarpos.emvsample.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.iisysgroup.poslib.host.entities.TransactionResult;
import com.wizarpos.emvsample.R;
import com.wizarpos.emvsample.adapters.TransactionHistory;
import com.wizarpos.emvsample.db.EodModel;
import com.wizarpos.emvsample.db.TransactionResultService;

import java.util.ArrayList;
import java.util.List;

public class RefundActivity extends FuncActivity {
    TransactionHistory adapter;
    private Button buttonBack;
    private TextView textTitle;
    RelativeLayout empty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.refund_activity);
        buttonBack = (Button)findViewById(R.id.btn_back);
        textTitle = (TextView)findViewById(R.id.tAppTitle);
        textTitle.setText("Refund List");
        empty = findViewById(R.id.empty);


        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestFuncMenu();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        TransactionResultService transactionResultService = new TransactionResultService(appState.db,this);
        ArrayList<EodModel> models = transactionResultService.getALllTransactions();
        if(models == null){
            empty.setVisibility(View.VISIBLE);
        }
        if(models != null){
            if(models.size() > 0){
                empty.setVisibility(View.VISIBLE);
            }
        }
        setUpRecyclerView(models);
        adapter.notifyDataSetChanged();
    }

    private void setUpRecyclerView(ArrayList<EodModel> object) {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
         adapter = new TransactionHistory(object,this,appState,this);
        recyclerView.setAdapter(adapter);

        LinearLayoutManager mLinearLayoutManagerVertical = new LinearLayoutManager(this); // (Context context, int spanCount)
        mLinearLayoutManagerVertical.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(mLinearLayoutManagerVertical);

    }
}
