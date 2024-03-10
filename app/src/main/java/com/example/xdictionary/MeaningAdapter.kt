package com.example.xdictionary

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.xdictionary.databinding.MeaningRecyclerRowBinding

class MeaningAdapter(private var meaningList: List<Meaning>) :
    RecyclerView.Adapter<MeaningAdapter.MeaningViewHolder>() {

    class MeaningViewHolder(private val binding: MeaningRecyclerRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(meaning: Meaning) {

            binding.partOfSpeechTextView.text = meaning.partOfSpeech
            binding.definitionsTextView.text = meaning.definitions.joinToString("\n\n") {
                var currentIndex = meaning.definitions.indexOf(it)
                (currentIndex + 1).toString() + ". " + it.definition
            }

            if (meaning.synonyms.isEmpty()) {
                binding.synonymsTitleTextView.visibility = View.GONE
                binding.synonymsTextView.visibility = View.GONE
            } else {
                binding.synonymsTitleTextView.visibility = View.VISIBLE
                binding.synonymsTextView.visibility = View.VISIBLE
                binding.synonymsTextView.text = meaning.synonyms.joinToString(", ")
            }

            if (meaning.antonyms.isEmpty()) {
                binding.antonymsTitleTextView.visibility = View.GONE
                binding.antonymsTextView.visibility = View.GONE
            } else {
                binding.antonymsTitleTextView.visibility = View.VISIBLE
                binding.antonymsTextView.visibility = View.VISIBLE
                binding.antonymsTextView.text = meaning.antonyms.joinToString(", ")
            }

        }
    }

    fun updateNewData(newMeaningList: List<Meaning>) {
        meaningList = newMeaningList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MeaningViewHolder {
        val binding =
            MeaningRecyclerRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MeaningViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return meaningList.size
    }

    override fun onBindViewHolder(holder: MeaningViewHolder, position: Int) {
        holder.bind(meaningList[position])
    }
}