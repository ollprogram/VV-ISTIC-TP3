package fr.istic.vv;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.never;

public class TLSSocketFactoryTestMocks {


    /**
     * Test when the edge case when the both supported and enabled protocols are null.
     */
    @Test
    public void preparedSocket_NullProtocols()  {

        SSLSocket sslSocketMockito = Mockito.mock(SSLSocket.class);

        TLSSocketFactory f = new TLSSocketFactory();

        Mockito.when(sslSocketMockito.getSupportedProtocols()).thenReturn(null);

        Mockito.when(sslSocketMockito.getEnabledProtocols()).thenReturn(null);

        f.prepareSocket(sslSocketMockito);

        Mockito.verify(sslSocketMockito, never()).setEnabledProtocols(null);
    }

    @Test
    public void typical()  {
        SSLSocket sslSocketMockito = Mockito.mock(SSLSocket.class);

        TLSSocketFactory f = new TLSSocketFactory();

        Mockito.when(sslSocketMockito.getSupportedProtocols()).thenReturn(
                shuffle(new String[]{"SSLv2Hello", "SSLv3", "TLSv1", "TLSv1.1", "TLSv1.2"})
        );
        Mockito.when(sslSocketMockito.getEnabledProtocols()).thenReturn(
                shuffle(new String[]{"SSLv3", "TLSv1"}) );

        f.prepareSocket(sslSocketMockito);

        Mockito.verify(sslSocketMockito).setEnabledProtocols(new String[] {"TLSv1.2", "TLSv1.1", "TLSv1", "SSLv3" });


    }



    private String[] shuffle(String[] in) {
        List<String> list = new ArrayList<String>(Arrays.asList(in));
        Collections.shuffle(list);
        return list.toArray(new String[0]);
    }



}