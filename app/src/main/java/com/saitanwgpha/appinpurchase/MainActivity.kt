package com.saitanwgpha.appinpurchase

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.anjlab.android.iab.v3.BillingProcessor
import com.anjlab.android.iab.v3.TransactionDetails
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), BillingProcessor.IBillingHandler{
    override fun onBillingInitialized() {

    }

    override fun onPurchaseHistoryRestored() {

    }

    override fun onProductPurchased(productId: String, details: TransactionDetails?) {
       Toast.makeText(this, "Purchased has done!", Toast.LENGTH_LONG).show()
    }

    override fun onBillingError(errorCode: Int, error: Throwable?) {

    }

    //variables
    lateinit var bp : BillingProcessor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bp = BillingProcessor(this, null, this)

        //MARK: Button
        btn_pay.setOnClickListener(View.OnClickListener {
            bp.purchase(this, "android.test.purchased")
        })
    }

    fun btnRestore(view: View){
        bp.loadOwnedPurchasesFromGoogle();
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        //Todo: request user | if bp is not use, use defualt
        if (!bp.handleActivityResult(requestCode, resultCode, data))
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onDestroy() {
        if (bp!=null)
            bp.release()
        super.onDestroy()
    }
}
