/*
 * Copyright (C) 2015 Mantas Varnagiris.
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 */

package com.mvcoding.financius.feature.tag

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.mvcoding.financius.feature.BaseAdapter
import rx.Observable
import rx.lang.kotlin.PublishSubject

class TagsAdapter() : BaseAdapter<Tag, TagsAdapter.ViewHolder>() {
    val tagSelectedSubject = PublishSubject<Tag>()
    var displayType: TagsPresenter.DisplayType = TagsPresenter.DisplayType.VIEW
    var selectedTags: Set<Tag> = setOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tagItemView.setTag(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(TagItemView.inflate(parent))
    }

    class ViewHolder(itemView: TagItemView, val tagItemView: TagItemView = itemView) : RecyclerView.ViewHolder(itemView)

    fun setTagSelected(tag: Tag, selected: Boolean) {
        if (selected) {
            selectedTags = selectedTags.plus(tag)
        } else {
            selectedTags = selectedTags.minus(tag)
        }
    }

    fun onTagSelected(): Observable<Tag> {
        return tagSelectedSubject
    }
}