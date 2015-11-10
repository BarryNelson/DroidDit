/**
 * Created by Barry on 11/28/2012.
*/

package com.nelsoft.droiddit.share;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class EMailActivity extends Activity {

	/**
	 * launch an email Intent and exit.
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Intent intent = new Intent(android.content.Intent.ACTION_SEND);
		intent.setType("plain/text");
		intent.putExtra(Intent.EXTRA_SUBJECT, "");
		intent.putExtra(Intent.EXTRA_TEXT, "");
		
		// keep it to real email programs
		intent.setType("message/rfc822");
		
		startActivity(Intent.createChooser(intent, "Send email using"));
		
		// close out this activity 
		this.finish();
		
	}
}