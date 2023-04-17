package com.pra.roomdatabase.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pra.roomdatabase.databinding.ItemProductBinding
import com.pra.roomdatabase.model.Products
import com.pra.roomdatabase.onItemClickListener

class ProductAdapter(
    private val context: Context,
    public val product: ArrayList<Products>,
    public val onItemClickListener: onItemClickListener
) : RecyclerView.Adapter<ProductAdapter.MyViewHolder>() {


    fun UpdateProduct(newProduct: List<Products>) {
        product.clear()
        product.addAll(newProduct)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemBinding = ItemProductBinding.inflate(LayoutInflater.from(context), parent, false)
        return MyViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemBinding.tvProductTitle.text = product[position].title

        Glide.with(context).load(product[position].image).centerCrop()
            .into(holder.itemBinding.ivProductImage);

        holder.itemBinding.ivLeftDelete.setOnClickListener {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(position,TypeClicked.LeftDelete)
            }
        }
        holder.itemBinding.ivLeftEdit.setOnClickListener {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(position,TypeClicked.LeftEdit)
            }
        }
        holder.itemBinding.ivRightEdit.setOnClickListener {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(position,TypeClicked.RightEdit)
            }
        }
        holder.itemBinding.ivRightDelete.setOnClickListener {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(position,TypeClicked.RightDelete)
            }
        }
    }

    override fun getItemCount(): Int {
        return product.size
    }


    class MyViewHolder(public val itemBinding: ItemProductBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(products: Products) {

        }
    }


}