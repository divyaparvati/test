import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory as CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as MobileBuiltInKeywords
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testcase.TestCaseFactory as TestCaseFactory
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testdata.TestDataFactory as TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository as ObjectRepository
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WSBuiltInKeywords
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUiBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

import com.ge.tms.commonactions.RailroadActions
import com.ge.tms.util.CommonUtilities
import com.ge.tms.constants.RailroadPageConstants

/**
 * Test Steps: Edit Track and cancel the edit
 *
 * 1. Login to Application
 * 2. Select random Railroad and add station under that railroad
 * 3. Add new zone under that station
 * 4. Add new track under that zone
 * 5. Select the newly added track
 * 6. Click on the edit icon
 * 7. Edit one of the fields
 * 8. Click on cancel
 * 9. Edited details for the track shall not be saved in the row
 * 10. Delete the zone
 * 11. Delete the station
 /*
  
  /**
 * Create the instance of the common util class
 */
CommonUtilities util = new CommonUtilities()

/**
 *  Action class for railroad
 */
RailroadActions actions = new RailroadActions()

/**
 * Click on the Railroad Setup
 */
CustomKeywords.'com.ge.tms.commonactions.CommonClickEvents.clickOnRailroadSetup'()

/**
 * Click on the Select Railroad dropdown
 */
CustomKeywords.'com.ge.tms.commonactions.RailroadActions.clickOnSelectRailroadDropdown'()

/**
 * Select any random railroad from the the Select railroad dropdown
 */
CustomKeywords.'com.ge.tms.commonactions.RailroadActions.selectRandomRailRoad'()

WebUI.delay(2)

/**
 * Create a new Station 
 * Set the station id as null for a new creation
 */
def setStationId;

actions.addStationMandatotyFields(setStationId)

WebUI.delay(2)

/**
 * Create a zone under that station
 * set the zone id as null for a new creation
 */
def setZoneId;

actions.addZone(setZoneId)

/**
 * To click on the Zone Save button
 */
CustomKeywords.'com.ge.tms.commonactions.RailroadActions.clickOnZoneSaveButton'()

WebUI.delay(2)

/**
 * Create a new track under the zone 
 * set the track id as null for new creation
 */
def setTrackId;

actions.saveNewTrackRecord(setTrackId)

/**
 *  Save Track
 */
actions.saveTrack()

WebUI.delay(1)

/**
 *  Verify the edited Track length is saved
 */
CustomKeywords.'com.ge.tms.railroad.RailroadVerification.verifyEditedTrackLengthNotSaved'()

/**
 * Click on the delete button for the created track
*/
CustomKeywords.'com.ge.tms.commonactions.RailroadActions.clickOnDeleteTrack'()

/**
 * Test object obtained for Yes button in Delete confirmation box
 */
deleteYesButtonObj = findTestObject('Object Repository/F5362 - Configure and Manage Track/deleteTrackYesButton')

/**
* Click on the Yes button to confirm the delete action of track
*/
CustomKeywords.'com.ge.tms.commonactions.RailroadActions.clickOnTestObject'(deleteYesButtonObj)

/**
* Verify that track has been successfully deleted
*/
CustomKeywords.'com.ge.tms.railroad.RailroadVerification.verifyNoTracksFoundInSearch'(actions.trackId)

WebUI.delay(2)

/**
 * Click on the Actions menu of the View Zone
 */
CustomKeywords.'com.ge.tms.commonactions.RailroadActions.clickOnZoneActionMenu'()

/**
 * Click on Delete zone
 */
CustomKeywords.'com.ge.tms.commonactions.RailroadActions.clickOnDeleteAction'(RailroadPageConstants.CLICK_DELETE_ZONE_BUTTON)

WebUI.delay(1)

/**
 * Test object obtained for Yes button in Delete confirmation box
 */
deleteYesButtonObj = findTestObject('Object Repository/F5140-Add Zone Functionalities/QA-US88570-Add Zone/US38196-Save a New Zone/Delete Zone/deleteYesButton')

/**
* Click on the Yes button to confirm the delete action of zone
*/
CustomKeywords.'com.ge.tms.commonactions.RailroadActions.clickOnTestObject'(deleteYesButtonObj)

WebUI.delay(1)

/**
* Verify that after successful delete of a zone user is naviagted back to the station view
*/
CustomKeywords.'com.ge.tms.railroad.RailroadVerification.verifyViewStationIdAfterZoneDeletion'()

/**
* Click on the Actions menu in View station
*/
CustomKeywords.'com.ge.tms.commonactions.RailroadActions.clickOnStationActionMenu'()

/**
* Click on Delete station button
*/
CustomKeywords.'com.ge.tms.commonactions.RailroadActions.clickOnDeleteStationButton'()

/**
 * Test object obtained for Yes button in Delete confirmation box
 */
deleteYesButtonObj = findTestObject('Object Repository/F11822-Configure and Manage Stations/deleteConfirmationYesButton')

/**
* Click on the Yes button to confirm the delete action of station
*/
CustomKeywords.'com.ge.tms.commonactions.RailroadActions.clickOnTestObject'(deleteYesButtonObj)

WebUI.delay(1)

/**
* Verify that after station deletion , deleted station shall not be searched in left navigation
*/
CustomKeywords.'com.ge.tms.railroad.RailroadVerification.verifyDeletedItemNotPresentInSearchView'(actions.stationId)
