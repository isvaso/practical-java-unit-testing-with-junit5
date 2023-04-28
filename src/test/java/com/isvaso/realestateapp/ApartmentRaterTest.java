package com.isvaso.realestateapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

class ApartmentRaterTest {

    @Nested
    class RateApartmentTests {

        @ParameterizedTest(name = "area: {0} m^2, price: ${1}, expectedRating: {2}")
        @CsvSource(value = {
                "120.0, 680000.0, 0",
                "62.0, 446000.0, 1",
                "26.0, 245000, 2"
        })
        void should_ReturnCorrectRating_When_CorrectApartment(double area,
                                                              String price,
                                                              int expectedRating) {
            // given
            Apartment apartment = new Apartment(area, new BigDecimal(price));

            // when
            int apartmentRating = ApartmentRater.rateApartment(apartment);

            // then
            assertEquals(expectedRating, apartmentRating);
        }

        @ParameterizedTest(name = "area: {0} m^2, price: ${1}, expectedRating: {2}")
        @CsvSource(value = {
                "0.0, 30000.0, -1",
                "-62.0, 20000.0, -1",
                "62.0, -20000.0, -1"
        })
        void should_ReturnErrorRating_When_IncorrectApartment(double area,
                                                              String price,
                                                              int expectedRating) {
            // given
            Apartment apartment = new Apartment(area, new BigDecimal(price));

            // when
            int apartmentRating = ApartmentRater.rateApartment(apartment);

            // then
            assumeTrue(expectedRating == apartmentRating);
        }
    }

    @Nested
    class CalculateAverageRatingTests {

        List<Apartment> apartmentList;

        @Test
        void should_CalculateCorrectAverageRating_When_CorrectApartmentList() {
            // given
            apartmentList = new ArrayList<>(Arrays.asList(
                    new Apartment(122.0D, new BigDecimal(900000)),
                    new Apartment(66.0D, new BigDecimal(567000)),
                    new Apartment(36.0D, new BigDecimal(1260000)),
                    new Apartment(96.0D, new BigDecimal(1260000)),
                    new Apartment(116.0D, new BigDecimal(677000)),
                    new Apartment(82.0D, new BigDecimal(720000)),
                    new Apartment(280.0D, new BigDecimal(999000))
            ));

            // when
            double averageRating = ApartmentRater.calculateAverageRating(apartmentList);
            averageRating = Math.round(averageRating * 10000.0) / 10000.0;

            // then
            assertEquals(1.2857D, averageRating);
        }

        @Test
        void should_ThrowException_When_ApartmentListIsEmpty() {
            // given
            apartmentList = new ArrayList<>();

            // when
            Executable executable = () -> ApartmentRater.calculateAverageRating(apartmentList);

            // then
            assertThrows(RuntimeException.class, executable);
        }
    }


}