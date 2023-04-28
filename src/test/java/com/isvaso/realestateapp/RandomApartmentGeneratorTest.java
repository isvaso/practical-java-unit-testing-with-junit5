package com.isvaso.realestateapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;

import java.math.BigDecimal;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

class RandomApartmentGeneratorTest {

    private final double MAX_MULTIPLIER = 4.0;

    @Nested
    class DefaultParamsApartmentGeneratorTests {

        private double minArea = 30.0;
        private BigDecimal minPricePerSquareMeter = new BigDecimal(3000.0);
        private RandomApartmentGenerator generator;

        @BeforeEach
        void setupRandomApartmentGenerator() {
            generator = new RandomApartmentGenerator();
        }

        @RepeatedTest(10)
        void should_ReturnCorrectApartment_when_DefaultMinAreaAndMinPrice() {
            // given
            double minArea = this.minArea;
            double maxArea = minArea * MAX_MULTIPLIER;
            BigDecimal minPricePerSquareMeter = this.minPricePerSquareMeter;

            // when
            Apartment apartment = generator.generate();
            BigDecimal maxPricePerSquareMeter = new BigDecimal(MAX_MULTIPLIER)
                    .multiply(minPricePerSquareMeter);
            BigDecimal minApartmentPrice = BigDecimal.valueOf(apartment.getArea())
                    .multiply(minPricePerSquareMeter);
            BigDecimal maxApartmentPrice = BigDecimal.valueOf(apartment.getArea())
                    .multiply(maxPricePerSquareMeter);

            // then
            assertAll(
                    () -> assertTrue(apartment.getArea() >= minArea),
                    () -> assertTrue(apartment.getArea() <= maxArea),
                    () -> assertTrue(apartment.getPrice()
                            .compareTo(minApartmentPrice) >= 0),
                    () -> assertTrue(apartment.getPrice()
                            .compareTo(maxApartmentPrice) <= 0)
            );
        }
    }

    @Nested
    class CustomCorrectParamsApartmentGeneratorTests {

        private Random random;
        private double minArea;
        private BigDecimal minPricePerSquareMeter;
        private RandomApartmentGenerator generator;

        @BeforeEach
        void setupRandomApartmentGenerator() {
            random = new Random();
            minArea = random.nextInt(20, 30);
            minPricePerSquareMeter = BigDecimal.valueOf(
                    random.nextInt(3000, 5000));
            generator = new RandomApartmentGenerator(minArea, minPricePerSquareMeter);
        }

        @RepeatedTest(10)
        void should_ReturnCorrectApartment_when_CustomMinAreaAndMinPrice() {
            // given
            double minArea = this.minArea;
            double maxArea = minArea * MAX_MULTIPLIER;
            BigDecimal minPricePerSquareMeter = this.minPricePerSquareMeter;

            // when
            Apartment apartment = generator.generate();
            BigDecimal maxPricePerSquareMeter = new BigDecimal(MAX_MULTIPLIER)
                    .multiply(minPricePerSquareMeter);
            BigDecimal minApartmentPrice = BigDecimal.valueOf(apartment.getArea())
                    .multiply(minPricePerSquareMeter);
            BigDecimal maxApartmentPrice = BigDecimal.valueOf(apartment.getArea())
                    .multiply(maxPricePerSquareMeter);

            // then
            assertAll(
                    () -> assertTrue(apartment.getArea() >= minArea),
                    () -> assertTrue(apartment.getArea() <= maxArea),
                    () -> assertTrue(apartment.getPrice()
                            .compareTo(minApartmentPrice) >= 0),
                    () -> assertTrue(apartment.getPrice()
                            .compareTo(maxApartmentPrice) <= 0)
            );
        }
    }

    @Nested
    class CustomIncorrectParamsApartmentGeneratorTests {

        private Random random;
        private double minArea;
        private BigDecimal minPricePerSquareMeter;
        private RandomApartmentGenerator generator;

        @BeforeEach
        void setupRandomApartmentGenerator() {
            random = new Random();
            minArea = random.nextInt(-20, 30);
            minPricePerSquareMeter = BigDecimal.valueOf(
                    random.nextInt(-3000, 5000));
            generator = new RandomApartmentGenerator(minArea, minPricePerSquareMeter);
        }

        @RepeatedTest(10)
        void try_ReturnApartment_when_MayBeIncorrectCustomMinAreaAndMinPrice() {
            // given
            double minArea = this.minArea;
            double maxArea = minArea * MAX_MULTIPLIER;
            BigDecimal minPricePerSquareMeter = this.minPricePerSquareMeter;

            // when
            Apartment apartment = generator.generate();
            BigDecimal maxPricePerSquareMeter = new BigDecimal(MAX_MULTIPLIER)
                    .multiply(minPricePerSquareMeter);
            BigDecimal minApartmentPrice = BigDecimal.valueOf(apartment.getArea())
                    .multiply(minPricePerSquareMeter);
            BigDecimal maxApartmentPrice = BigDecimal.valueOf(apartment.getArea())
                    .multiply(maxPricePerSquareMeter);

            // then
            assumeTrue(apartment.getArea() >= minArea);
            assumeTrue(apartment.getArea() <= maxArea);
            assumeTrue(apartment.getPrice()
                    .compareTo(minApartmentPrice) >= 0);
            assumeTrue(apartment.getPrice()
                    .compareTo(maxApartmentPrice) <= 0);
        }
    }
}