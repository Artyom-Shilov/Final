package by.shilov.newsSite.service;

import by.shilov.newsSite.dao.Transaction;

abstract public class ServiceImpl implements Service {
    protected Transaction transaction;

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }
}
