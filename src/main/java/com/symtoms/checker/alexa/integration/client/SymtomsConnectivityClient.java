package com.symtoms.checker.alexa.integration.client;

import com.symtoms.checker.alexa.data.SymtomsRequest;

public interface SymtomsConnectivityClient <T> {

	public T invokeRequest(SymtomsRequest request, Class<T> clazz);
}
