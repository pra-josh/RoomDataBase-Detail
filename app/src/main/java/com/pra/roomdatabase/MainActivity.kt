package com.pra.roomdatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.pra.roomdatabase.adapter.ProductAdapter
import com.pra.roomdatabase.adapter.TypeClicked
import com.pra.roomdatabase.api.WebApiClient
import com.pra.roomdatabase.app.MyApplication
import com.pra.roomdatabase.databinding.ActivityMainBinding
import com.pra.roomdatabase.db.AppDataBase
import com.pra.roomdatabase.model.Products
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity(), onItemClickListener {

    private var mBinding: ActivityMainBinding? = null

    private var webApiClient: WebApiClient? = null

    private lateinit var appDataBase: AppDataBase
    private var mAdapter: ProductAdapter = ProductAdapter(this, arrayListOf(), this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding?.root)


      //  appDataBase = AppDataBase.getDataBase(applicationContext)
         appDataBase = MyApplication.getAppInstance()?.getRoomDataBaseInstance(this)!!

        mBinding?.rvUser?.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = mAdapter
        }


        mBinding?.swipeRefresh?.setOnRefreshListener {
            mBinding?.swipeRefresh?.isRefreshing = false
            prepareView()
        }

        webApiClient = WebApiClient(applicationContext)

        prepareView()
    }


    private fun prepareView() {
        mBinding?.progressBar?.visibility = View.VISIBLE
        val responseCall = webApiClient?.getWebapi()?.getProductUser()

        responseCall?.enqueue(object : retrofit2.Callback<List<Products>> {
            override fun onResponse(
                call: Call<List<Products>>, response: Response<List<Products>>
            ) {
                mBinding?.progressBar?.visibility = View.GONE
                if (response.isSuccessful && response.body()?.isNotEmpty()!!) {
                    val product = response.body()
                    for (i in product?.indices!!) {
                        appDataBase.roomDao().addProduct(product[i])
                    }

                    mBinding?.progressBar?.visibility = View.GONE

                    setAdapter()
                } else {
                    mBinding?.tvError?.visibility = View.VISIBLE
                }
            }

            override fun onFailure(call: Call<List<Products>>, t: Throwable) {
                mBinding?.progressBar?.visibility = View.GONE
                mBinding?.tvError?.visibility = View.VISIBLE
            }
        })

    }

    var _products: List<Products> = ArrayList()

    private fun setAdapter() {
        val productList = appDataBase.roomDao().getProduct()
        for (i in productList.indices) {
            println("Product title ========>" + productList[i].title)
        }
        _products = productList;
        mAdapter.UpdateProduct(_products)
        mAdapter.notifyDataSetChanged()
    }

    override fun onItemClick(position: Int, typeClicked: TypeClicked) {
        if (typeClicked == TypeClicked.LeftEdit) {
            var product = _products[position]
            product.title = "Left Edit title";
            appDataBase.roomDao().updateProduct(product)
            setAdapter()
        } else if (typeClicked == TypeClicked.LeftDelete) {
            var product = _products[position]
            appDataBase.roomDao().deleteProduct(product)
            setAdapter()
        } else if (typeClicked == TypeClicked.RightDelete) {
            val product = _products[position]
            appDataBase.roomDao().deleteProduct(product)
            setAdapter()
        } else if (typeClicked == TypeClicked.RightEdit) {
            val product = _products[position]
            product.title = "Right Edit"
            appDataBase.roomDao().updateProduct(product)
            setAdapter()
        }
    }


    /*private fun fetchCountry() {
        loading.value = true
        var responsecall: Call<RandomUserApiResponse> = webApiClient?.getRandomUser()!!
        responsecall.enqueue(object : Callback<RandomUserApiResponse> {
            override fun onResponse(
                call: Call<RandomUserApiResponse>,
                response: Response<RandomUserApiResponse>
            ) {
                loading.value = false
                when {
                    response.isSuccessful -> {
                        val responseBody = response.body()
                        if (response != null) {
                            val randomUserApiResponse = responseBody
                            var list: List<User> = ArrayList()
                            runBlocking {
                                val deferred = async {
                                    mAppDb.userDaO().deleteAllUser()
                                }
                                val wait = deferred.await()
                                val savedeferred = async {
                                    mAppDb.userDaO().saveUser(randomUserApiResponse?.users!!)
                                }
                                val waitSave = savedeferred.await()

                                val select = async {
                                    list = mAppDb.userDaO().getAllUser()
                                }
                            }

                            println("size of Db=====>${list.size}")
                            users.value = list
                        } else {
                            countryLoadError.value = true
                        }
                    }
                    response.errorBody() != null -> countryLoadError.value = true
                    else -> countryLoadError.value = true
                }
            }

            override fun onFailure(call: Call<RandomUserApiResponse>, t: Throwable) {
                loading.value = false
                countryLoadError.value = true
            }

        })


    }*/


}