package Classes

import android.media.Image
import android.provider.ContactsContract
import android.security.identity.AccessControlProfile

class User(var username: String, var pass: String, var email:String, var name: String, var birth: String, profileImage: Image, var shelter: Boolean) {
}
