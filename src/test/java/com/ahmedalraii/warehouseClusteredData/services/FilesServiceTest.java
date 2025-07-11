package com.ahmedalraii.warehouseClusteredData.services;

import com.ahmedalraii.warehouseClusteredData.models.Deal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class FilesServiceTest  {

    @Mock
    FileService service;

    @Test
   void isDealsEqualsShouldReturnTrue(){

        // arrange
        Deal dealOne = new Deal("OMR" ,"USD" , "2025-07-11" , 1000 );
        Deal dealTwo = new Deal("OMR" ,"USD" , "2025-07-11" , 1000 );
        Mockito.when(service.isDealsEquals(dealOne ,dealTwo)).thenReturn(true);

        // act
        boolean res = service.isDealsEquals(dealOne , dealTwo);

        // assert
        Assertions.assertTrue(res);

    }

    @Test
    void isDealsEqualsShouldReturnFalse(){

        // arrange
        Deal dealOne = new Deal("OMR" ,"USD" , "2025-07-01" , 1000 );
        Deal dealTwo = new Deal("OMR" ,"USD" , "2025-07-03" , 3000 );
        Mockito.when(service.isDealsEquals(dealOne ,dealTwo)).thenReturn(false);

        // act
        boolean res = service.isDealsEquals(dealOne , dealTwo);

        // assert
        Assertions.assertFalse(res);

    }
}
