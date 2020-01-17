package com.wizarpos.emvsample.db.detailed.vas.model;

import java.lang.System;

/**
 * * Created by Olije Favour on 12/12/2019.
 * *Copyright (c) 2019    All rights reserved.
 */
@kotlinx.android.parcel.Parcelize()
@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000A\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0010\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0010\b\n\u0003\b\u008e\u0001\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u00cf\u0003\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0012\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014\u0012\b\u0010\u0015\u001a\u0004\u0018\u00010\u0014\u0012\b\u0010\u0016\u001a\u0004\u0018\u00010\u0014\u0012\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u0018\u0012\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u001a\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u001b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u001c\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u001d\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u001e\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u001f\u001a\u00020\u0003\u0012\b\b\u0002\u0010 \u001a\u00020\u0003\u0012\b\b\u0002\u0010!\u001a\u00020\u0003\u0012\b\b\u0002\u0010\"\u001a\u00020\u0003\u0012\b\b\u0002\u0010#\u001a\u00020\u0003\u0012\b\b\u0002\u0010$\u001a\u00020%\u0012\b\b\u0002\u0010&\u001a\u00020\u0003\u0012\b\b\u0002\u0010\'\u001a\u00020\u0018\u0012\b\b\u0002\u0010(\u001a\u00020\u0003\u0012\u0006\u0010)\u001a\u00020\u0003\u0012\u0006\u0010*\u001a\u00020\u0003\u0012\u0006\u0010+\u001a\u00020\u0003\u0012\b\b\u0002\u0010,\u001a\u00020\u0003\u0012\b\b\u0002\u0010-\u001a\u00020\u0003\u0012\b\b\u0002\u0010.\u001a\u00020\u0003\u0012\b\b\u0002\u0010/\u001a\u00020\u0003\u0012\b\b\u0002\u00100\u001a\u00020\u0003\u0012\b\b\u0002\u00101\u001a\u00020\u0003\u0012\b\b\u0002\u00102\u001a\u00020\u0003\u0012\b\b\u0002\u00103\u001a\u00020\u0003\u00a2\u0006\u0002\u00104J\f\u0010\u0081\u0001\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\f\u0010\u0082\u0001\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\f\u0010\u0083\u0001\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\f\u0010\u0084\u0001\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\f\u0010\u0085\u0001\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\f\u0010\u0086\u0001\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\f\u0010\u0087\u0001\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\f\u0010\u0088\u0001\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u0011\u0010\u0089\u0001\u001a\u0004\u0018\u00010\u0014H\u00c6\u0003\u00a2\u0006\u0002\u0010FJ\u0011\u0010\u008a\u0001\u001a\u0004\u0018\u00010\u0014H\u00c6\u0003\u00a2\u0006\u0002\u0010FJ\u0011\u0010\u008b\u0001\u001a\u0004\u0018\u00010\u0014H\u00c6\u0003\u00a2\u0006\u0002\u0010FJ\f\u0010\u008c\u0001\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u0011\u0010\u008d\u0001\u001a\u0004\u0018\u00010\u0018H\u00c6\u0003\u00a2\u0006\u0002\u0010YJ\f\u0010\u008e\u0001\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\n\u0010\u008f\u0001\u001a\u00020\u0003H\u00c6\u0003J\n\u0010\u0090\u0001\u001a\u00020\u0003H\u00c6\u0003J\n\u0010\u0091\u0001\u001a\u00020\u0003H\u00c6\u0003J\n\u0010\u0092\u0001\u001a\u00020\u0003H\u00c6\u0003J\n\u0010\u0093\u0001\u001a\u00020\u0003H\u00c6\u0003J\n\u0010\u0094\u0001\u001a\u00020\u0003H\u00c6\u0003J\n\u0010\u0095\u0001\u001a\u00020\u0003H\u00c6\u0003J\n\u0010\u0096\u0001\u001a\u00020\u0003H\u00c6\u0003J\f\u0010\u0097\u0001\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\n\u0010\u0098\u0001\u001a\u00020\u0003H\u00c6\u0003J\n\u0010\u0099\u0001\u001a\u00020\u0003H\u00c6\u0003J\n\u0010\u009a\u0001\u001a\u00020%H\u00c6\u0003J\n\u0010\u009b\u0001\u001a\u00020\u0003H\u00c6\u0003J\n\u0010\u009c\u0001\u001a\u00020\u0018H\u00c6\u0003J\n\u0010\u009d\u0001\u001a\u00020\u0003H\u00c6\u0003J\n\u0010\u009e\u0001\u001a\u00020\u0003H\u00c6\u0003J\n\u0010\u009f\u0001\u001a\u00020\u0003H\u00c6\u0003J\n\u0010\u00a0\u0001\u001a\u00020\u0003H\u00c6\u0003J\n\u0010\u00a1\u0001\u001a\u00020\u0003H\u00c6\u0003J\f\u0010\u00a2\u0001\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\n\u0010\u00a3\u0001\u001a\u00020\u0003H\u00c6\u0003J\n\u0010\u00a4\u0001\u001a\u00020\u0003H\u00c6\u0003J\n\u0010\u00a5\u0001\u001a\u00020\u0003H\u00c6\u0003J\n\u0010\u00a6\u0001\u001a\u00020\u0003H\u00c6\u0003J\n\u0010\u00a7\u0001\u001a\u00020\u0003H\u00c6\u0003J\n\u0010\u00a8\u0001\u001a\u00020\u0003H\u00c6\u0003J\n\u0010\u00a9\u0001\u001a\u00020\u0003H\u00c6\u0003J\f\u0010\u00aa\u0001\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\f\u0010\u00ab\u0001\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\f\u0010\u00ac\u0001\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\f\u0010\u00ad\u0001\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\f\u0010\u00ae\u0001\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u0086\u0004\u0010\u00af\u0001\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00142\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00142\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00142\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u00182\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u001a\u001a\u00020\u00032\b\b\u0002\u0010\u001b\u001a\u00020\u00032\b\b\u0002\u0010\u001c\u001a\u00020\u00032\b\b\u0002\u0010\u001d\u001a\u00020\u00032\b\b\u0002\u0010\u001e\u001a\u00020\u00032\b\b\u0002\u0010\u001f\u001a\u00020\u00032\b\b\u0002\u0010 \u001a\u00020\u00032\b\b\u0002\u0010!\u001a\u00020\u00032\b\b\u0002\u0010\"\u001a\u00020\u00032\b\b\u0002\u0010#\u001a\u00020\u00032\b\b\u0002\u0010$\u001a\u00020%2\b\b\u0002\u0010&\u001a\u00020\u00032\b\b\u0002\u0010\'\u001a\u00020\u00182\b\b\u0002\u0010(\u001a\u00020\u00032\b\b\u0002\u0010)\u001a\u00020\u00032\b\b\u0002\u0010*\u001a\u00020\u00032\b\b\u0002\u0010+\u001a\u00020\u00032\b\b\u0002\u0010,\u001a\u00020\u00032\b\b\u0002\u0010-\u001a\u00020\u00032\b\b\u0002\u0010.\u001a\u00020\u00032\b\b\u0002\u0010/\u001a\u00020\u00032\b\b\u0002\u00100\u001a\u00020\u00032\b\b\u0002\u00101\u001a\u00020\u00032\b\b\u0002\u00102\u001a\u00020\u00032\b\b\u0002\u00103\u001a\u00020\u0003H\u00c6\u0001\u00a2\u0006\u0003\u0010\u00b0\u0001J\n\u0010\u00b1\u0001\u001a\u00020%H\u00d6\u0001J\u0016\u0010\u00b2\u0001\u001a\u00020\u00182\n\u0010\u00b3\u0001\u001a\u0005\u0018\u00010\u00b4\u0001H\u00d6\u0003J\n\u0010\u00b5\u0001\u001a\u00020%H\u00d6\u0001J\n\u0010\u00b6\u0001\u001a\u00020\u0003H\u00d6\u0001J\u001e\u0010\u00b7\u0001\u001a\u00030\u00b8\u00012\b\u0010\u00b9\u0001\u001a\u00030\u00ba\u00012\u0007\u0010\u00bb\u0001\u001a\u00020%H\u00d6\u0001R\u001c\u0010\u0012\u001a\u0004\u0018\u00010\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b5\u00106\"\u0004\b7\u00108R\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b9\u00106\"\u0004\b:\u00108R\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b;\u00106\"\u0004\b<\u00108R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b=\u00106\"\u0004\b>\u00108R\u001c\u0010\u0010\u001a\u0004\u0018\u00010\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b?\u00106\"\u0004\b@\u00108R\u001c\u0010\u0011\u001a\u0004\u0018\u00010\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bA\u00106\"\u0004\bB\u00108R\u001c\u0010\n\u001a\u0004\u0018\u00010\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bC\u00106\"\u0004\bD\u00108R\u001e\u0010\u0015\u001a\u0004\u0018\u00010\u0014X\u0086\u000e\u00a2\u0006\u0010\n\u0002\u0010I\u001a\u0004\bE\u0010F\"\u0004\bG\u0010HR\u0011\u00101\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\bJ\u00106R\u001e\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0086\u000e\u00a2\u0006\u0010\n\u0002\u0010I\u001a\u0004\bK\u0010F\"\u0004\bL\u0010HR\u0011\u00102\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\bM\u00106R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bN\u00106\"\u0004\bO\u00108R\u001c\u0010\u000b\u001a\u0004\u0018\u00010\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bP\u00106\"\u0004\bQ\u00108R\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bR\u00106\"\u0004\bS\u00108R\u001c\u0010\u000e\u001a\u0004\u0018\u00010\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bT\u00106\"\u0004\bU\u00108R\u0011\u0010&\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\bV\u00106R\u0011\u0010\'\u001a\u00020\u0018\u00a2\u0006\b\n\u0000\u001a\u0004\bW\u0010XR\u001e\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0086\u000e\u00a2\u0006\u0010\n\u0002\u0010\\\u001a\u0004\b\u0017\u0010Y\"\u0004\bZ\u0010[R\u0011\u0010$\u001a\u00020%\u00a2\u0006\b\n\u0000\u001a\u0004\b]\u0010^R\u001e\u0010\u0016\u001a\u0004\u0018\u00010\u0014X\u0086\u000e\u00a2\u0006\u0010\n\u0002\u0010I\u001a\u0004\b_\u0010F\"\u0004\b`\u0010HR\u0011\u0010\u001b\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\ba\u00106R\u0011\u0010\u001d\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\bb\u00106R\u0011\u0010\u001c\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\bc\u00106R\u001c\u0010\f\u001a\u0004\u0018\u00010\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bd\u00106\"\u0004\be\u00108R\u0011\u0010\u001e\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\bf\u00106R\u0011\u0010/\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\bg\u00106R\u0011\u0010*\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\bh\u00106R\u0011\u0010#\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\bi\u00106R\u0011\u0010\u001f\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\bj\u00106R\u0011\u0010)\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\bk\u00106R\u001c\u0010\b\u001a\u0004\u0018\u00010\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bl\u00106\"\u0004\bm\u00108R\u0013\u0010\u0019\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\bn\u00106R\u0011\u00103\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\bo\u00106R\u001c\u0010\r\u001a\u0004\u0018\u00010\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bp\u00106\"\u0004\bq\u00108R\u0011\u00100\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\br\u00106R\u0011\u0010+\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\bs\u00106R\u0011\u0010\"\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\bt\u00106R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bu\u00106\"\u0004\bv\u00108R\u001a\u0010 \u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bw\u00106\"\u0004\bx\u00108R\u001c\u0010\t\u001a\u0004\u0018\u00010\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\by\u00106\"\u0004\bz\u00108R\u0011\u0010,\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b{\u00106R\u0011\u0010-\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b|\u00106R\u0011\u0010!\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b}\u00106R\u0011\u0010(\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b~\u00106R\u0011\u0010.\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u007f\u00106R\u0012\u0010\u001a\u001a\u00020\u0003\u00a2\u0006\t\n\u0000\u001a\u0005\b\u0080\u0001\u00106\u00a8\u0006\u00bc\u0001"}, d2 = {"Lcom/wizarpos/emvsample/db/detailed/vas/model/DiscoCardResult;", "Landroid/os/Parcelable;", "RRN", "", "authID", "STAN", "PAN", "transactionStatus", "responseCode", "transactionStatusReason", "accountType", "batchNumber", "merchantID", "terminalID", "cardHolderName", "cardExpiry", "TSI", "TVR", "AID", "amount", "", "additionalAmount", "longDateTime", "isRolledBack", "", "stan", "walletId", "marchantAddress", "marchantTid", "marchantName", "merchantId", "product", "transactionStatusMessage", "vasTid", "transactionRef", "paymentmethod", "logo", "", "dateTime", "error", "vasType", "recepiant_name", "meterType", "transactionId", "unit", "unit_value", "vat", "meterNumber", "token", "address", "arras", "tarrif", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ZLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getAID", "()Ljava/lang/String;", "setAID", "(Ljava/lang/String;)V", "getPAN", "setPAN", "getRRN", "setRRN", "getSTAN", "setSTAN", "getTSI", "setTSI", "getTVR", "setTVR", "getAccountType", "setAccountType", "getAdditionalAmount", "()Ljava/lang/Long;", "setAdditionalAmount", "(Ljava/lang/Long;)V", "Ljava/lang/Long;", "getAddress", "getAmount", "setAmount", "getArras", "getAuthID", "setAuthID", "getBatchNumber", "setBatchNumber", "getCardExpiry", "setCardExpiry", "getCardHolderName", "setCardHolderName", "getDateTime", "getError", "()Z", "()Ljava/lang/Boolean;", "setRolledBack", "(Ljava/lang/Boolean;)V", "Ljava/lang/Boolean;", "getLogo", "()I", "getLongDateTime", "setLongDateTime", "getMarchantAddress", "getMarchantName", "getMarchantTid", "getMerchantID", "setMerchantID", "getMerchantId", "getMeterNumber", "getMeterType", "getPaymentmethod", "getProduct", "getRecepiant_name", "getResponseCode", "setResponseCode", "getStan", "getTarrif", "getTerminalID", "setTerminalID", "getToken", "getTransactionId", "getTransactionRef", "getTransactionStatus", "setTransactionStatus", "getTransactionStatusMessage", "setTransactionStatusMessage", "getTransactionStatusReason", "setTransactionStatusReason", "getUnit", "getUnit_value", "getVasTid", "getVasType", "getVat", "getWalletId", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component2", "component20", "component21", "component22", "component23", "component24", "component25", "component26", "component27", "component28", "component29", "component3", "component30", "component31", "component32", "component33", "component34", "component35", "component36", "component37", "component38", "component39", "component4", "component40", "component41", "component42", "component43", "component44", "component45", "component46", "component5", "component6", "component7", "component8", "component9", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ZLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lcom/wizarpos/emvsample/db/detailed/vas/model/DiscoCardResult;", "describeContents", "equals", "other", "", "hashCode", "toString", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "wari-wari_online_release"})
public final class DiscoCardResult implements android.os.Parcelable {
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
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String stan = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String walletId = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String marchantAddress = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String marchantTid = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String marchantName = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String merchantId = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String product = null;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String transactionStatusMessage;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String vasTid = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String transactionRef = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String paymentmethod = null;
    private final int logo = 0;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String dateTime = null;
    private final boolean error = false;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String vasType = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String recepiant_name = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String meterType = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String transactionId = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String unit = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String unit_value = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String vat = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String meterNumber = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String token = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String address = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String arras = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String tarrif = null;
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
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getStan() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getWalletId() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getMarchantAddress() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getMarchantTid() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getMarchantName() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getMerchantId() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getProduct() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getTransactionStatusMessage() {
        return null;
    }
    
    public final void setTransactionStatusMessage(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getVasTid() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getTransactionRef() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getPaymentmethod() {
        return null;
    }
    
    public final int getLogo() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getDateTime() {
        return null;
    }
    
    public final boolean getError() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getVasType() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getRecepiant_name() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getMeterType() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getTransactionId() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getUnit() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getUnit_value() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getVat() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getMeterNumber() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getToken() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getAddress() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getArras() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getTarrif() {
        return null;
    }
    
    public DiscoCardResult(@org.jetbrains.annotations.Nullable()
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
    java.lang.Boolean isRolledBack, @org.jetbrains.annotations.Nullable()
    java.lang.String stan, @org.jetbrains.annotations.NotNull()
    java.lang.String walletId, @org.jetbrains.annotations.NotNull()
    java.lang.String marchantAddress, @org.jetbrains.annotations.NotNull()
    java.lang.String marchantTid, @org.jetbrains.annotations.NotNull()
    java.lang.String marchantName, @org.jetbrains.annotations.NotNull()
    java.lang.String merchantId, @org.jetbrains.annotations.NotNull()
    java.lang.String product, @org.jetbrains.annotations.NotNull()
    java.lang.String transactionStatusMessage, @org.jetbrains.annotations.NotNull()
    java.lang.String vasTid, @org.jetbrains.annotations.NotNull()
    java.lang.String transactionRef, @org.jetbrains.annotations.NotNull()
    java.lang.String paymentmethod, int logo, @org.jetbrains.annotations.NotNull()
    java.lang.String dateTime, boolean error, @org.jetbrains.annotations.NotNull()
    java.lang.String vasType, @org.jetbrains.annotations.NotNull()
    java.lang.String recepiant_name, @org.jetbrains.annotations.NotNull()
    java.lang.String meterType, @org.jetbrains.annotations.NotNull()
    java.lang.String transactionId, @org.jetbrains.annotations.NotNull()
    java.lang.String unit, @org.jetbrains.annotations.NotNull()
    java.lang.String unit_value, @org.jetbrains.annotations.NotNull()
    java.lang.String vat, @org.jetbrains.annotations.NotNull()
    java.lang.String meterNumber, @org.jetbrains.annotations.NotNull()
    java.lang.String token, @org.jetbrains.annotations.NotNull()
    java.lang.String address, @org.jetbrains.annotations.NotNull()
    java.lang.String arras, @org.jetbrains.annotations.NotNull()
    java.lang.String tarrif) {
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
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component21() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component22() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component23() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component24() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component25() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component26() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component27() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component28() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component29() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component30() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component31() {
        return null;
    }
    
    public final int component32() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component33() {
        return null;
    }
    
    public final boolean component34() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component35() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component36() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component37() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component38() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component39() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component40() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component41() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component42() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component43() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component44() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component45() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component46() {
        return null;
    }
    
    /**
     * * Created by Olije Favour on 12/12/2019.
     * *Copyright (c) 2019    All rights reserved.
     */
    @org.jetbrains.annotations.NotNull()
    public final com.wizarpos.emvsample.db.detailed.vas.model.DiscoCardResult copy(@org.jetbrains.annotations.Nullable()
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
    java.lang.Boolean isRolledBack, @org.jetbrains.annotations.Nullable()
    java.lang.String stan, @org.jetbrains.annotations.NotNull()
    java.lang.String walletId, @org.jetbrains.annotations.NotNull()
    java.lang.String marchantAddress, @org.jetbrains.annotations.NotNull()
    java.lang.String marchantTid, @org.jetbrains.annotations.NotNull()
    java.lang.String marchantName, @org.jetbrains.annotations.NotNull()
    java.lang.String merchantId, @org.jetbrains.annotations.NotNull()
    java.lang.String product, @org.jetbrains.annotations.NotNull()
    java.lang.String transactionStatusMessage, @org.jetbrains.annotations.NotNull()
    java.lang.String vasTid, @org.jetbrains.annotations.NotNull()
    java.lang.String transactionRef, @org.jetbrains.annotations.NotNull()
    java.lang.String paymentmethod, int logo, @org.jetbrains.annotations.NotNull()
    java.lang.String dateTime, boolean error, @org.jetbrains.annotations.NotNull()
    java.lang.String vasType, @org.jetbrains.annotations.NotNull()
    java.lang.String recepiant_name, @org.jetbrains.annotations.NotNull()
    java.lang.String meterType, @org.jetbrains.annotations.NotNull()
    java.lang.String transactionId, @org.jetbrains.annotations.NotNull()
    java.lang.String unit, @org.jetbrains.annotations.NotNull()
    java.lang.String unit_value, @org.jetbrains.annotations.NotNull()
    java.lang.String vat, @org.jetbrains.annotations.NotNull()
    java.lang.String meterNumber, @org.jetbrains.annotations.NotNull()
    java.lang.String token, @org.jetbrains.annotations.NotNull()
    java.lang.String address, @org.jetbrains.annotations.NotNull()
    java.lang.String arras, @org.jetbrains.annotations.NotNull()
    java.lang.String tarrif) {
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