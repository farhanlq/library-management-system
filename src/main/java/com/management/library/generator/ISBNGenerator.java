package com.management.library.generator;

import java.io.Serializable;
import java.util.Calendar;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class ISBNGenerator implements IdentifierGenerator {

	private String prefix = "EMP_";

	public int generateCustId() {
		int randomPIN = (int) (Math.random() * 9000) + 1000;
		return randomPIN;
	}

	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
		Calendar calendar = Calendar.getInstance();
		return prefix + this.generateCustId() + "_" + calendar.get(Calendar.YEAR);
	}

}
