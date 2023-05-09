# ATA-Week-3
Before running any of the commands ensure you first run `./gradlew build`

## Commands

### Encapsulation
1. `./gradlew encapsulationrationalnumber-test` : Failed Tests - a. equals_withUpdatedReducedNonReducedRationals_returnsEqual() b. update_withZeroDenominator_throwsException()
2. `./gradlew encapsulationspecies-test`: Failed Tests a. setPopulation_withNegative_throwsException () b. predictPopulation_withNegativeGrowthRate_returnsZero ()


### Interfaces
1. `./gradlew interfacesreversedstring-test` : Failed Tests - a.  charAt_firstIndex_returnsFirstChar() b.  toString_nonemptyString_returnsReversedString() c. charAt_lastIndex_returnsLastChar() d. subSequence_validSubSequence_returnsCorrectSubsequence()
2. `./gradlew interfacesdevices-test` : All four initial tests passed


### Primitive Wrapper Classes / Group Work
1. `./gradlew genericsdatalogger-test` : Failed Tests - a.  dataConstructor_withNullData_constructsInstance() b.   dataAndTimestampConstructor_withNullData_constructsInstance() c.  dataConstructor_withData_defaultsTimestampToNow() d.  dataAndTimestampConstructor_withNonNullValues_constructsInstance()
2. `./gradlew genericsdatalogger-main` : Writes  logData() isn't fully implemented yet.
3. `./gradlew genericsrecommender-test` : Failed Tests - a. getRecommendation_videoSelectedHasSimilarVideo_returnsSimilarVideo() b.  getRecommendation_singleVideoWatched_returnsRecommendation()
4. `./gradlew genericswatcher-test` : Failed Tests - a. watch_nonExistentPrimeVideo_throwIllegalArgumentsException() b.  watch_watchPrimeVideo_addToMostRecentlyViewed()
5. `./gradlew genericsrostermanager-main` : Writes ERROR: Unsuccessful in marking Participant with id: 7 as absent from TUESDAY .
6. `./gradlew genericsrostermanager-test` : Failed Tests - a. scheduleSwapFailsTest() b. scheduleAbsenceFailsTest() c. addParticipantsToSectionTest() d. addParticipantsToSectionTwiceTest() e. scheduleAbsenceTest() f. scheduleSwapTest() 