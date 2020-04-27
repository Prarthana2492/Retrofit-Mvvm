package com.FarmPe.Oxkart;


  public class Urls {

    private static final String ROOT_URL = "http://13.232.185.209:909/api/";///DEV
 // private static final String ROOT_URL = "http://13.233.184.72:909/api/";///pro

    public static final String IMAGE_ROOT_URL = "http://13.233.184.72:909";//Image root
    public static final String Add_New_Address = ROOT_URL+"MasterTable/AddUserAddress";
    public static final String GetFarmerDetailsList = ROOT_URL+"MasterTable/GetFarmersList";
    public static final String LOGIN=ROOT_URL+"Auth/ValidateUser";
    public static final String SIGNUP=ROOT_URL+"Auth/RegisterUser";


    public static final String Languages=ROOT_URL+"MasterTable/GetLanguages";
    public static final String Forgot_Password=ROOT_URL+"Auth/ForgotPassword";
    public static final String ChangePassword=ROOT_URL+"Auth/ChangePassword";
    public static final String ResendOTP = ROOT_URL +"Auth/ResendOTPforUser";


    public static final String VerifyOTPNewUser=ROOT_URL+"Auth/VerifyOTPNewUser";
    public static final String GetUserDetails=ROOT_URL+"GetUserDetails";
    public static final String ValidateReferalCode=ROOT_URL+"ValidateRefferalCode";

    public static final String CHANGE_LANGUAGE= ROOT_URL+"Lang/ChangeCurrentCulture";

     //New_Login_Register_Details

      public static final String New_Register_Details = ROOT_URL + "Auth/RegisterNewUser";


      public static final String New_Login_Details = ROOT_URL + "Auth/ValidateRegisteredUser";


    //Get_Address
      public static final String Get_New_Address = ROOT_URL+"MasterTable/GetUserAddress";


    // Wallet
      public static final String GetFarmDetailsList = ROOT_URL+"MasterTable/GetFarmsList";
      public static final String GetFarmsListByUserId = "MasterTable/GetFarmsListByUserId";


    // Refer n Earn
    public static final String Refferal_Code = ROOT_URL +"Auth/GetUserDetails";


    //Wallet balance
    public static final String GetWalletDetails = ROOT_URL +"MasterTable/GetWalletDetails";



    // Address
    public static final String Delete_Address_Details = ROOT_URL + "MasterTable/DeleteUserAddress";
    public static final String Default_Address = ROOT_URL + "MasterTable/UpdateUserDefaultAddress";
    public static final String Edit_Address = ROOT_URL + "MasterTable/UpdateUserDefaultAddress";


   //feedback

    public static final String AddFeedback = ROOT_URL + "MasterTable/AddFeedback";


     //profile details

      // public static final String Get_Profile_Details= ROOT_URL + "Auth/GetUserDetails";
       public static final String Get_Profile_Details= ROOT_URL + "Auth/GetRegUserDetails";


       public static final String Update_Profile_Details= ROOT_URL + "Auth/UpdateRegProfile";

       //r_u_farmer
       public static final String R_U_Farmer_Details= ROOT_URL + "MasterTable/AddUpdateUserDetails";




   //Notification

         public static final String GET_NOTIFICATION= ROOT_URL + "MasterTable/GetNotificationMaster";
         public static final String GET_NOTIFICATIONLIST= ROOT_URL + "MasterTable/GetNotifications";
         public static final String UPDATEUSERNOTIFICATIONSETTING= ROOT_URL + "Auth/UpdateUserNotificationSettings";



         public static final String Districts=ROOT_URL+"MasterTable/GetDistricts";
         public static final String Taluks=ROOT_URL+"MasterTable/GetTaluks";
         public static final String Hoblis=ROOT_URL+"MasterTable/GetHoblis";
         public static final String Block_list=ROOT_URL+"MasterTable/GetBlocks";



         public static final String Villages=ROOT_URL+"MasterTable/GetVillages";
         public static final String State = ROOT_URL+"MasterTable/GetStates";
         public static final String GetBrandList = ROOT_URL+"MasterTable/GetBrandList";
         public static final String ModelList = ROOT_URL+"MasterTable/GetModels";
         public static final String GetHPList = ROOT_URL+"MasterTable/GetHPList";
         public static final String AddRequestForQuotation = ROOT_URL+"MasterTable/AddUpdateRequestForQuotation";
         public static final String GetLookingForItems = ROOT_URL+"MasterTable/GetLookingForDetails";
         public static final String GetLookingForFirst = ROOT_URL+"MasterTable/GetLookingFor";
         public static final String GetLookingForList = ROOT_URL+"MasterTable/GetLookingForLists";
         public static final String YourRequest = ROOT_URL+"MasterTable/GetLookingForListsById";
         public static final String Model_List = ROOT_URL+"MasterTable/GetRFQModelMasterList";
         public static final String Get_RFQ_Details = ROOT_URL + "MasterTable/GetRFQDetails";
         public static final String Get_RFQ_Model_Details = ROOT_URL + "MasterTable/GetRFQDetailsfromList";




       //Favorites
        public static final String Get_Favorites = ROOT_URL+"MasterTable/GetFarmerFavouriteModelLists";
        public static final String Add_Favorites = ROOT_URL+"MasterTable/AddUpdateFarmerFavouriteModelList";


        //List Your Farms

          public static final String List_Your_Farms = ROOT_URL+"MasterTable/GetFarmCategoryList";
          public static final String Farm_Type_List = ROOT_URL+"MasterTable/GetFarmTypesList";
          public static final String Farm_Details = ROOT_URL+"MasterTable/AddUpdateFarms";
          public static final String AddUpdateFarms = ROOT_URL+"MasterTable/AddUpdateFarms";
          public static final String Invitation_Farm = ROOT_URL+"MasterTable/GetInvitationList";
          public static final String Invitn_accpt_cancel = ROOT_URL+"MasterTable/RespondToConnectionRequest";



       //Shop_location_Details


        public static final String Add_Update_Image_Details = ROOT_URL+"MasterTable/AddUpdateCImageDetails";
        public static final String Get_Image_Details = ROOT_URL+"MasterTable/GetCImagelist";
        public static final String Add_CurrentLocation = ROOT_URL+"MasterTable/AddUpdateLocationDetails";
        public static final String Get_Shop_Location = ROOT_URL + "MasterTable/GetCLocationList";



        public static final String Add_Front_Voter_ID_Details = ROOT_URL + "MasterTable/AddUpdateVoterIdFrontDetails";
        public static final String Add_Back_Voter_ID_Details = ROOT_URL + "MasterTable/AddUpdateVoterIdBackDetails";
        public static final String Get_Front_Voter_ID_Details = ROOT_URL + "MasterTable/GetVoterIdFrontList";
        public static final String Get_Back_Voter_ID_Details = ROOT_URL + "MasterTable/GetVoterIdBackList";



        public static final String Add_Update_Aadhar_details = ROOT_URL + "MasterTable/AddUpdateAadhaarDetails";
        public static final String Get_KYC_details = ROOT_URL + "MasterTable/GetPANDetails";
        public static final String Get_Voter_ID_Details = ROOT_URL + "MasterTable/GetVoterIdList";
        public static final String Get_Verification_Status = ROOT_URL + "MasterTable/GetUserVerificationStatus";





     //Request Quoatation

       public static final String Get_Edit_Request = ROOT_URL + "MasterTable/GetLookingForListsById";
       public static final String Delete_Request = ROOT_URL + "MasterTable/DeleteRequestForQuotation";


    //Bank_Account_Details

      public static final String Add_Bank_Details =  ROOT_URL + "MasterTable/AddUpdatBankDetails";
      public static final String Get_Bank_Details =  ROOT_URL + "MasterTable/GetBankDetails";
      public static final String Delete_Bank_Details =  ROOT_URL + "MasterTable/DeleteBankDetails";


    //Profile Address

      public static final String Profile_Add_Adress_Details = ROOT_URL + "MasterTable/AddUpdateAddressDetails";
      public static final String Profile_Get_Adress_Details = ROOT_URL + "MasterTable/GetAddressDetailsByUser";
      public static final String Profile_Delete_Adress_Details =  ROOT_URL +  "MasterTable/DeleteAddressDetails";
      public static final String Block_List =  ROOT_URL +  "MasterTable/GetBlocks";
      public static final String Village_list =  ROOT_URL +  "MasterTable/GetVillagebyBlock";
      public static final String Village_Panchayats_List =  ROOT_URL + "MasterTable/GetVillagebyGram";


      //Connections
       public static final String Get_Connection_List = ROOT_URL + "MasterTable/GetConnectionList";



       //Homepage_Count
       public static final String Home_Page_Count = ROOT_URL + "MasterTable/GetCountForFarmer";


       //Notification
       public static final String Notification_HomePage = ROOT_URL + "MasterTable/GetNotifications";

}
