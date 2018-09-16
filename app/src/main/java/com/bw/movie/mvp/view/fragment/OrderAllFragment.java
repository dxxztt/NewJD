package com.bw.movie.mvp.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bw.movie.R;
import com.bw.movie.adapter.OrderAllAdapter;
import com.bw.movie.bean.CreateOrder;
import com.bw.movie.bean.OrderList;
import com.bw.movie.mvp.presenter.MyOrderPresenter;
import com.bw.movie.mvp.view.OrderView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * author:Created by YangYong on 2018/8/20 0020.
 */
public class OrderAllFragment extends Fragment implements OrderView{
    @BindView(R.id.orderall_rv)
    RecyclerView orderallRv;
    Unbinder unbinder;
    private MyOrderPresenter presenter;
    private int uid;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.orderall_fg, container, false);
        unbinder = ButterKnife.bind(this, view);
        presenter = new MyOrderPresenter(this);
        uid = getActivity().getSharedPreferences("user", 0).getInt("uid", 0);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        orderallRv.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        orderallRv.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        presenter.getOrderList(uid+"",3+"");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onGetOrderSuccess(CreateOrder createOrder) {

    }

    @Override
    public void onGetOrderListSuccess(OrderList orderList) {
        List<OrderList.DataBean> data = orderList.getData();
        OrderAllAdapter allAdapter = new OrderAllAdapter(data);
        orderallRv.setAdapter(allAdapter);
    }
}
