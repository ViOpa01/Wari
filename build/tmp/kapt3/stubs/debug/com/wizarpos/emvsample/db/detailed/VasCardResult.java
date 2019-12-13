package com.wizarpos.emvsample.db.detailed;

import java.lang.System;

/**
 * * Created by Olije Favour on 11/10/2019.
 * *Copyright (c) 2019    All rights reserved.
 */
@kotlinx.android.parcel.Parcelize()
@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000?\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0010\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0003\b\u0090\u0001\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u00f7\u0002\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0012\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014\u0012\b\u0010\u0015\u001a\u0004\u0018\u00010\u0014\u0012\b\u0010\u0016\u001a\u0004\u0018\u00010\u0014\u0012\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u0018\u0012\u0006\u0010\u0019\u001a\u00020\u001a\u0012\b\u0010\u001b\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u001c\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u001d\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u001e\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u001f\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010 \u001a\u0004\u0018\u00010\u0003\u0012\b\u0010!\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\"\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010#\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010$\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010%\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010&\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\'\u001a\u00020\u0014\u0012\b\u0010(\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010)\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010*\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0002\u0010+J\f\u0010\u0081\u0001\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\f\u0010\u0082\u0001\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\f\u0010\u0083\u0001\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\f\u0010\u0084\u0001\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\f\u0010\u0085\u0001\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\f\u0010\u0086\u0001\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\f\u0010\u0087\u0001\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\f\u0010\u0088\u0001\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u0011\u0010\u0089\u0001\u001a\u0004\u0018\u00010\u0014H\u00c6\u0003\u00a2\u0006\u0002\u0010?J\u0011\u0010\u008a\u0001\u001a\u0004\u0018\u00010\u0014H\u00c6\u0003\u00a2\u0006\u0002\u0010?J\u0011\u0010\u008b\u0001\u001a\u0004\u0018\u00010\u0014H\u00c6\u0003\u00a2\u0006\u0002\u0010?J\f\u0010\u008c\u0001\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u0011\u0010\u008d\u0001\u001a\u0004\u0018\u00010\u0018H\u00c6\u0003\u00a2\u0006\u0002\u0010_J\n\u0010\u008e\u0001\u001a\u00020\u001aH\u00c6\u0003J\f\u0010\u008f\u0001\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\f\u0010\u0090\u0001\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\f\u0010\u0091\u0001\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\f\u0010\u0092\u0001\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\f\u0010\u0093\u0001\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\f\u0010\u0094\u0001\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\f\u0010\u0095\u0001\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\f\u0010\u0096\u0001\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\f\u0010\u0097\u0001\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\f\u0010\u0098\u0001\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\f\u0010\u0099\u0001\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\f\u0010\u009a\u0001\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\f\u0010\u009b\u0001\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\n\u0010\u009c\u0001\u001a\u00020\u0014H\u00c6\u0003J\f\u0010\u009d\u0001\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\f\u0010\u009e\u0001\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\f\u0010\u009f\u0001\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\f\u0010\u00a0\u0001\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\f\u0010\u00a1\u0001\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\f\u0010\u00a2\u0001\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\f\u0010\u00a3\u0001\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\f\u0010\u00a4\u0001\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\f\u0010\u00a5\u0001\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u00c8\u0003\u0010\u00a6\u0001\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00142\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00142\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00142\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u00182\b\b\u0002\u0010\u0019\u001a\u00020\u001a2\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010#\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010$\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010%\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010&\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\'\u001a\u00020\u00142\n\b\u0002\u0010(\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010)\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010*\u001a\u0004\u0018\u00010\u0003H\u00c6\u0001\u00a2\u0006\u0003\u0010\u00a7\u0001J\n\u0010\u00a8\u0001\u001a\u00020\u001aH\u00d6\u0001J\u0016\u0010\u00a9\u0001\u001a\u00020\u00182\n\u0010\u00aa\u0001\u001a\u0005\u0018\u00010\u00ab\u0001H\u00d6\u0003J\n\u0010\u00ac\u0001\u001a\u00020\u001aH\u00d6\u0001J\n\u0010\u00ad\u0001\u001a\u00020\u0003H\u00d6\u0001J\u001e\u0010\u00ae\u0001\u001a\u00030\u00af\u00012\b\u0010\u00b0\u0001\u001a\u00030\u00b1\u00012\u0007\u0010\u00b2\u0001\u001a\u00020\u001aH\u00d6\u0001R\u001c\u0010\u0012\u001a\u0004\u0018\u00010\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b,\u0010-\"\u0004\b.\u0010/R\u001c\u0010\u001f\u001a\u0004\u0018\u00010\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b0\u0010-\"\u0004\b1\u0010/R\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b2\u0010-\"\u0004\b3\u0010/R\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b4\u0010-\"\u0004\b5\u0010/R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b6\u0010-\"\u0004\b7\u0010/R\u001c\u0010\u0010\u001a\u0004\u0018\u00010\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b8\u0010-\"\u0004\b9\u0010/R\u001c\u0010\u0011\u001a\u0004\u0018\u00010\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b:\u0010-\"\u0004\b;\u0010/R\u001c\u0010\n\u001a\u0004\u0018\u00010\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b<\u0010-\"\u0004\b=\u0010/R\u001e\u0010\u0015\u001a\u0004\u0018\u00010\u0014X\u0086\u000e\u00a2\u0006\u0010\n\u0002\u0010B\u001a\u0004\b>\u0010?\"\u0004\b@\u0010AR\u001e\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0086\u000e\u00a2\u0006\u0010\n\u0002\u0010B\u001a\u0004\bC\u0010?\"\u0004\bD\u0010AR\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bE\u0010-\"\u0004\bF\u0010/R\u001c\u0010\u001d\u001a\u0004\u0018\u00010\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bG\u0010-\"\u0004\bH\u0010/R\u001c\u0010\u000b\u001a\u0004\u0018\u00010\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bI\u0010-\"\u0004\bJ\u0010/R\u001c\u0010\u001c\u001a\u0004\u0018\u00010\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bK\u0010-\"\u0004\bL\u0010/R\u001c\u0010\u001e\u001a\u0004\u0018\u00010\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bM\u0010-\"\u0004\bN\u0010/R\u001c\u0010\u001b\u001a\u0004\u0018\u00010\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bO\u0010-\"\u0004\bP\u0010/R\u001c\u0010 \u001a\u0004\u0018\u00010\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bQ\u0010-\"\u0004\bR\u0010/R\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bS\u0010-\"\u0004\bT\u0010/R\u001c\u0010\u000e\u001a\u0004\u0018\u00010\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bU\u0010-\"\u0004\bV\u0010/R\u001a\u0010\'\u001a\u00020\u0014X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bW\u0010X\"\u0004\bY\u0010ZR\u001a\u0010\u0019\u001a\u00020\u001aX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b[\u0010\\\"\u0004\b]\u0010^R\u001e\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0086\u000e\u00a2\u0006\u0010\n\u0002\u0010b\u001a\u0004\b\u0017\u0010_\"\u0004\b`\u0010aR\u001e\u0010\u0016\u001a\u0004\u0018\u00010\u0014X\u0086\u000e\u00a2\u0006\u0010\n\u0002\u0010B\u001a\u0004\bc\u0010?\"\u0004\bd\u0010AR\u001c\u0010\f\u001a\u0004\u0018\u00010\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\be\u0010-\"\u0004\bf\u0010/R\u001c\u0010)\u001a\u0004\u0018\u00010\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bg\u0010-\"\u0004\bh\u0010/R\u001c\u0010\"\u001a\u0004\u0018\u00010\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bi\u0010-\"\u0004\bj\u0010/R\u001c\u0010!\u001a\u0004\u0018\u00010\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bk\u0010-\"\u0004\bl\u0010/R\u001c\u0010\b\u001a\u0004\u0018\u00010\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bm\u0010-\"\u0004\bn\u0010/R\u001c\u0010\r\u001a\u0004\u0018\u00010\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bo\u0010-\"\u0004\bp\u0010/R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bq\u0010-\"\u0004\br\u0010/R\u001c\u0010\t\u001a\u0004\u0018\u00010\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bs\u0010-\"\u0004\bt\u0010/R\u001c\u0010*\u001a\u0004\u0018\u00010\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bu\u0010-\"\u0004\bv\u0010/R\u001c\u0010#\u001a\u0004\u0018\u00010\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bw\u0010-\"\u0004\bx\u0010/R\u001c\u0010$\u001a\u0004\u0018\u00010\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\by\u0010-\"\u0004\bz\u0010/R\u001c\u0010&\u001a\u0004\u0018\u00010\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b{\u0010-\"\u0004\b|\u0010/R\u001c\u0010%\u001a\u0004\u0018\u00010\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b}\u0010-\"\u0004\b~\u0010/R\u001d\u0010(\u001a\u0004\u0018\u00010\u0003X\u0086\u000e\u00a2\u0006\u000f\n\u0000\u001a\u0004\b\u007f\u0010-\"\u0005\b\u0080\u0001\u0010/\u00a8\u0006\u00b3\u0001"}, d2 = {"Lcom/wizarpos/emvsample/db/detailed/VasCardResult;", "Landroid/os/Parcelable;", "RRN", "", "authID", "STAN", "PAN", "transactionStatus", "responseCode", "transactionStatusReason", "accountType", "batchNumber", "merchantID", "terminalID", "cardHolderName", "cardExpiry", "TSI", "TVR", "AID", "amount", "", "additionalAmount", "longDateTime", "isRolledBack", "", "id", "", "beneficiaryName", "beneficiaryAcc", "bankCode", "beneficiaryBank", "BenefeciaryEmail", "beneficiaryPhone", "requestId", "remarks", "type", "vasResponseCode", "vasTransactionStatus", "vasResponseDescription", "dateTime", "vasTransactionType", "paymentMethod", "transactionType", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Boolean;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getAID", "()Ljava/lang/String;", "setAID", "(Ljava/lang/String;)V", "getBenefeciaryEmail", "setBenefeciaryEmail", "getPAN", "setPAN", "getRRN", "setRRN", "getSTAN", "setSTAN", "getTSI", "setTSI", "getTVR", "setTVR", "getAccountType", "setAccountType", "getAdditionalAmount", "()Ljava/lang/Long;", "setAdditionalAmount", "(Ljava/lang/Long;)V", "Ljava/lang/Long;", "getAmount", "setAmount", "getAuthID", "setAuthID", "getBankCode", "setBankCode", "getBatchNumber", "setBatchNumber", "getBeneficiaryAcc", "setBeneficiaryAcc", "getBeneficiaryBank", "setBeneficiaryBank", "getBeneficiaryName", "setBeneficiaryName", "getBeneficiaryPhone", "setBeneficiaryPhone", "getCardExpiry", "setCardExpiry", "getCardHolderName", "setCardHolderName", "getDateTime", "()J", "setDateTime", "(J)V", "getId", "()I", "setId", "(I)V", "()Ljava/lang/Boolean;", "setRolledBack", "(Ljava/lang/Boolean;)V", "Ljava/lang/Boolean;", "getLongDateTime", "setLongDateTime", "getMerchantID", "setMerchantID", "getPaymentMethod", "setPaymentMethod", "getRemarks", "setRemarks", "getRequestId", "setRequestId", "getResponseCode", "setResponseCode", "getTerminalID", "setTerminalID", "getTransactionStatus", "setTransactionStatus", "getTransactionStatusReason", "setTransactionStatusReason", "getTransactionType", "setTransactionType", "getType", "setType", "getVasResponseCode", "setVasResponseCode", "getVasResponseDescription", "setVasResponseDescription", "getVasTransactionStatus", "setVasTransactionStatus", "getVasTransactionType", "setVasTransactionType", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component2", "component20", "component21", "component22", "component23", "component24", "component25", "component26", "component27", "component28", "component29", "component3", "component30", "component31", "component32", "component33", "component34", "component35", "component36", "component37", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Boolean;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lcom/wizarpos/emvsample/db/detailed/VasCardResult;", "describeContents", "equals", "other", "", "hashCode", "toString", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "Wariok_debug"})
public final class VasCardResult implements android.os.Parcelable {
    @org.jetbrains.annotations.Nullable()
    private java.lang.String RRN;
    @org.jetbrains.annotations.Nullable()
    private java.lang.String authID;
    @org.jetbrains.annotations.Nullable()
    private java.lang.String STAN;
    @org.jetbrains.annotations.Nullable()
    private java.lang.String PAN;
    @org.jetbrains.annotations.Nullable()
    private java.lang.String transactionStatus;
    @org.jetbrains.annotations.Nullable()
    private java.lang.String responseCode;
    @org.jetbrains.annotations.Nullable()
    private java.lang.String transactionStatusReason;
    @org.jetbrains.annotations.Nullable()
    private java.lang.String accountType;
    @org.jetbrains.annotations.Nullable()
    private java.lang.String batchNumber;
    @org.jetbrains.annotations.Nullable()
    private java.lang.String merchantID;
    @org.jetbrains.annotations.Nullable()
    private java.lang.String terminalID;
    @org.jetbrains.annotations.Nullable()
    private java.lang.String cardHolderName;
    @org.jetbrains.annotations.Nullable()
    private java.lang.String cardExpiry;
    @org.jetbrains.annotations.Nullable()
    private java.lang.String TSI;
    @org.jetbrains.annotations.Nullable()
    private java.lang.String TVR;
    @org.jetbrains.annotations.Nullable()
    private java.lang.String AID;
    @org.jetbrains.annotations.Nullable()
    private java.lang.Long amount;
    @org.jetbrains.annotations.Nullable()
    private java.lang.Long additionalAmount;
    @org.jetbrains.annotations.Nullable()
    private java.lang.Long longDateTime;
    @org.jetbrains.annotations.Nullable()
    private java.lang.Boolean isRolledBack;
    private int id;
    @org.jetbrains.annotations.Nullable()
    private java.lang.String beneficiaryName;
    @org.jetbrains.annotations.Nullable()
    private java.lang.String beneficiaryAcc;
    @org.jetbrains.annotations.Nullable()
    private java.lang.String bankCode;
    @org.jetbrains.annotations.Nullable()
    private java.lang.String beneficiaryBank;
    @org.jetbrains.annotations.Nullable()
    private java.lang.String BenefeciaryEmail;
    @org.jetbrains.annotations.Nullable()
    private java.lang.String beneficiaryPhone;
    @org.jetbrains.annotations.Nullable()
    private java.lang.String requestId;
    @org.jetbrains.annotations.Nullable()
    private java.lang.String remarks;
    @org.jetbrains.annotations.Nullable()
    private java.lang.String type;
    @org.jetbrains.annotations.Nullable()
    private java.lang.String vasResponseCode;
    @org.jetbrains.annotations.Nullable()
    private java.lang.String vasTransactionStatus;
    @org.jetbrains.annotations.Nullable()
    private java.lang.String vasResponseDescription;
    private long dateTime;
    @org.jetbrains.annotations.Nullable()
    private java.lang.String vasTransactionType;
    @org.jetbrains.annotations.Nullable()
    private java.lang.String paymentMethod;
    @org.jetbrains.annotations.Nullable()
    private java.lang.String transactionType;
    public static final android.os.Parcelable.Creator CREATOR = null;
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getRRN() {
        return null;
    }
    
    public final void setRRN(@org.jetbrains.annotations.Nullable()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getAuthID() {
        return null;
    }
    
    public final void setAuthID(@org.jetbrains.annotations.Nullable()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getSTAN() {
        return null;
    }
    
    public final void setSTAN(@org.jetbrains.annotations.Nullable()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getPAN() {
        return null;
    }
    
    public final void setPAN(@org.jetbrains.annotations.Nullable()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getTransactionStatus() {
        return null;
    }
    
    public final void setTransactionStatus(@org.jetbrains.annotations.Nullable()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getResponseCode() {
        return null;
    }
    
    public final void setResponseCode(@org.jetbrains.annotations.Nullable()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getTransactionStatusReason() {
        return null;
    }
    
    public final void setTransactionStatusReason(@org.jetbrains.annotations.Nullable()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getAccountType() {
        return null;
    }
    
    public final void setAccountType(@org.jetbrains.annotations.Nullable()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getBatchNumber() {
        return null;
    }
    
    public final void setBatchNumber(@org.jetbrains.annotations.Nullable()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getMerchantID() {
        return null;
    }
    
    public final void setMerchantID(@org.jetbrains.annotations.Nullable()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getTerminalID() {
        return null;
    }
    
    public final void setTerminalID(@org.jetbrains.annotations.Nullable()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getCardHolderName() {
        return null;
    }
    
    public final void setCardHolderName(@org.jetbrains.annotations.Nullable()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getCardExpiry() {
        return null;
    }
    
    public final void setCardExpiry(@org.jetbrains.annotations.Nullable()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getTSI() {
        return null;
    }
    
    public final void setTSI(@org.jetbrains.annotations.Nullable()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getTVR() {
        return null;
    }
    
    public final void setTVR(@org.jetbrains.annotations.Nullable()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getAID() {
        return null;
    }
    
    public final void setAID(@org.jetbrains.annotations.Nullable()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Long getAmount() {
        return null;
    }
    
    public final void setAmount(@org.jetbrains.annotations.Nullable()
    java.lang.Long p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Long getAdditionalAmount() {
        return null;
    }
    
    public final void setAdditionalAmount(@org.jetbrains.annotations.Nullable()
    java.lang.Long p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Long getLongDateTime() {
        return null;
    }
    
    public final void setLongDateTime(@org.jetbrains.annotations.Nullable()
    java.lang.Long p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Boolean isRolledBack() {
        return null;
    }
    
    public final void setRolledBack(@org.jetbrains.annotations.Nullable()
    java.lang.Boolean p0) {
    }
    
    public final int getId() {
        return 0;
    }
    
    public final void setId(int p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getBeneficiaryName() {
        return null;
    }
    
    public final void setBeneficiaryName(@org.jetbrains.annotations.Nullable()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getBeneficiaryAcc() {
        return null;
    }
    
    public final void setBeneficiaryAcc(@org.jetbrains.annotations.Nullable()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getBankCode() {
        return null;
    }
    
    public final void setBankCode(@org.jetbrains.annotations.Nullable()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getBeneficiaryBank() {
        return null;
    }
    
    public final void setBeneficiaryBank(@org.jetbrains.annotations.Nullable()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getBenefeciaryEmail() {
        return null;
    }
    
    public final void setBenefeciaryEmail(@org.jetbrains.annotations.Nullable()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getBeneficiaryPhone() {
        return null;
    }
    
    public final void setBeneficiaryPhone(@org.jetbrains.annotations.Nullable()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getRequestId() {
        return null;
    }
    
    public final void setRequestId(@org.jetbrains.annotations.Nullable()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getRemarks() {
        return null;
    }
    
    public final void setRemarks(@org.jetbrains.annotations.Nullable()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getType() {
        return null;
    }
    
    public final void setType(@org.jetbrains.annotations.Nullable()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getVasResponseCode() {
        return null;
    }
    
    public final void setVasResponseCode(@org.jetbrains.annotations.Nullable()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getVasTransactionStatus() {
        return null;
    }
    
    public final void setVasTransactionStatus(@org.jetbrains.annotations.Nullable()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getVasResponseDescription() {
        return null;
    }
    
    public final void setVasResponseDescription(@org.jetbrains.annotations.Nullable()
    java.lang.String p0) {
    }
    
    public final long getDateTime() {
        return 0L;
    }
    
    public final void setDateTime(long p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getVasTransactionType() {
        return null;
    }
    
    public final void setVasTransactionType(@org.jetbrains.annotations.Nullable()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getPaymentMethod() {
        return null;
    }
    
    public final void setPaymentMethod(@org.jetbrains.annotations.Nullable()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getTransactionType() {
        return null;
    }
    
    public final void setTransactionType(@org.jetbrains.annotations.Nullable()
    java.lang.String p0) {
    }
    
    public VasCardResult(@org.jetbrains.annotations.Nullable()
    java.lang.String RRN, @org.jetbrains.annotations.Nullable()
    java.lang.String authID, @org.jetbrains.annotations.Nullable()
    java.lang.String STAN, @org.jetbrains.annotations.Nullable()
    java.lang.String PAN, @org.jetbrains.annotations.Nullable()
    java.lang.String transactionStatus, @org.jetbrains.annotations.Nullable()
    java.lang.String responseCode, @org.jetbrains.annotations.Nullable()
    java.lang.String transactionStatusReason, @org.jetbrains.annotations.Nullable()
    java.lang.String accountType, @org.jetbrains.annotations.Nullable()
    java.lang.String batchNumber, @org.jetbrains.annotations.Nullable()
    java.lang.String merchantID, @org.jetbrains.annotations.Nullable()
    java.lang.String terminalID, @org.jetbrains.annotations.Nullable()
    java.lang.String cardHolderName, @org.jetbrains.annotations.Nullable()
    java.lang.String cardExpiry, @org.jetbrains.annotations.Nullable()
    java.lang.String TSI, @org.jetbrains.annotations.Nullable()
    java.lang.String TVR, @org.jetbrains.annotations.Nullable()
    java.lang.String AID, @org.jetbrains.annotations.Nullable()
    java.lang.Long amount, @org.jetbrains.annotations.Nullable()
    java.lang.Long additionalAmount, @org.jetbrains.annotations.Nullable()
    java.lang.Long longDateTime, @org.jetbrains.annotations.Nullable()
    java.lang.Boolean isRolledBack, int id, @org.jetbrains.annotations.Nullable()
    java.lang.String beneficiaryName, @org.jetbrains.annotations.Nullable()
    java.lang.String beneficiaryAcc, @org.jetbrains.annotations.Nullable()
    java.lang.String bankCode, @org.jetbrains.annotations.Nullable()
    java.lang.String beneficiaryBank, @org.jetbrains.annotations.Nullable()
    java.lang.String BenefeciaryEmail, @org.jetbrains.annotations.Nullable()
    java.lang.String beneficiaryPhone, @org.jetbrains.annotations.Nullable()
    java.lang.String requestId, @org.jetbrains.annotations.Nullable()
    java.lang.String remarks, @org.jetbrains.annotations.Nullable()
    java.lang.String type, @org.jetbrains.annotations.Nullable()
    java.lang.String vasResponseCode, @org.jetbrains.annotations.Nullable()
    java.lang.String vasTransactionStatus, @org.jetbrains.annotations.Nullable()
    java.lang.String vasResponseDescription, long dateTime, @org.jetbrains.annotations.Nullable()
    java.lang.String vasTransactionType, @org.jetbrains.annotations.Nullable()
    java.lang.String paymentMethod, @org.jetbrains.annotations.Nullable()
    java.lang.String transactionType) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component1() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component2() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component3() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component4() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component5() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component6() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component7() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component8() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component9() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component10() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component11() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component12() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component13() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component14() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component15() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component16() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Long component17() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Long component18() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Long component19() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Boolean component20() {
        return null;
    }
    
    public final int component21() {
        return 0;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component22() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component23() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component24() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component25() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component26() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component27() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component28() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component29() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component30() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component31() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component32() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component33() {
        return null;
    }
    
    public final long component34() {
        return 0L;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component35() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component36() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component37() {
        return null;
    }
    
    /**
     * * Created by Olije Favour on 11/10/2019.
     * *Copyright (c) 2019    All rights reserved.
     */
    @org.jetbrains.annotations.NotNull()
    public final com.wizarpos.emvsample.db.detailed.VasCardResult copy(@org.jetbrains.annotations.Nullable()
    java.lang.String RRN, @org.jetbrains.annotations.Nullable()
    java.lang.String authID, @org.jetbrains.annotations.Nullable()
    java.lang.String STAN, @org.jetbrains.annotations.Nullable()
    java.lang.String PAN, @org.jetbrains.annotations.Nullable()
    java.lang.String transactionStatus, @org.jetbrains.annotations.Nullable()
    java.lang.String responseCode, @org.jetbrains.annotations.Nullable()
    java.lang.String transactionStatusReason, @org.jetbrains.annotations.Nullable()
    java.lang.String accountType, @org.jetbrains.annotations.Nullable()
    java.lang.String batchNumber, @org.jetbrains.annotations.Nullable()
    java.lang.String merchantID, @org.jetbrains.annotations.Nullable()
    java.lang.String terminalID, @org.jetbrains.annotations.Nullable()
    java.lang.String cardHolderName, @org.jetbrains.annotations.Nullable()
    java.lang.String cardExpiry, @org.jetbrains.annotations.Nullable()
    java.lang.String TSI, @org.jetbrains.annotations.Nullable()
    java.lang.String TVR, @org.jetbrains.annotations.Nullable()
    java.lang.String AID, @org.jetbrains.annotations.Nullable()
    java.lang.Long amount, @org.jetbrains.annotations.Nullable()
    java.lang.Long additionalAmount, @org.jetbrains.annotations.Nullable()
    java.lang.Long longDateTime, @org.jetbrains.annotations.Nullable()
    java.lang.Boolean isRolledBack, int id, @org.jetbrains.annotations.Nullable()
    java.lang.String beneficiaryName, @org.jetbrains.annotations.Nullable()
    java.lang.String beneficiaryAcc, @org.jetbrains.annotations.Nullable()
    java.lang.String bankCode, @org.jetbrains.annotations.Nullable()
    java.lang.String beneficiaryBank, @org.jetbrains.annotations.Nullable()
    java.lang.String BenefeciaryEmail, @org.jetbrains.annotations.Nullable()
    java.lang.String beneficiaryPhone, @org.jetbrains.annotations.Nullable()
    java.lang.String requestId, @org.jetbrains.annotations.Nullable()
    java.lang.String remarks, @org.jetbrains.annotations.Nullable()
    java.lang.String type, @org.jetbrains.annotations.Nullable()
    java.lang.String vasResponseCode, @org.jetbrains.annotations.Nullable()
    java.lang.String vasTransactionStatus, @org.jetbrains.annotations.Nullable()
    java.lang.String vasResponseDescription, long dateTime, @org.jetbrains.annotations.Nullable()
    java.lang.String vasTransactionType, @org.jetbrains.annotations.Nullable()
    java.lang.String paymentMethod, @org.jetbrains.annotations.Nullable()
    java.lang.String transactionType) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public java.lang.String toString() {
        return null;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object p0) {
        return false;
    }
    
    @java.lang.Override()
    public int describeContents() {
        return 0;
    }
    
    @java.lang.Override()
    public void writeToParcel(@org.jetbrains.annotations.NotNull()
    android.os.Parcel parcel, int flags) {
    }
    
    @kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 3)
    public static final class Creator implements android.os.Parcelable.Creator {
        
        public Creator() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.Object[] newArray(int size) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.Object createFromParcel(@org.jetbrains.annotations.NotNull()
        android.os.Parcel in) {
            return null;
        }
    }
}