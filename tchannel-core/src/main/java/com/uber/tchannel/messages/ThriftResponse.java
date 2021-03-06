/*
 * Copyright (c) 2015 Uber Technologies, Inc.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.uber.tchannel.messages;

import com.uber.tchannel.api.ResponseCode;
import com.uber.tchannel.headers.ArgScheme;
import io.netty.buffer.ByteBuf;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public final class ThriftResponse<T> extends EncodedResponse<T> {

    private ThriftResponse(Builder<T> builder) {
        super(builder);
    }

    protected ThriftResponse(long id, ResponseCode responseCode,
                              Map<String, String> transportHeaders,
                              ByteBuf arg2, ByteBuf arg3) {
        super(id, responseCode, transportHeaders, arg2, arg3);
    }

    protected ThriftResponse(ErrorResponse error) {
        super(error);
    }

    /**
     * @param <T> response body type
     */
    public static class Builder<T> extends EncodedResponse.Builder<T> {

        public Builder(@NotNull ThriftRequest<?> req) {
            super(req);
            this.argScheme = ArgScheme.THRIFT;
        }

        @Override
        public @NotNull Builder<T> validate() {
            super.validate();
            return this;
        }

        public @NotNull ThriftResponse<T> build() {
            return new ThriftResponse<>(this.validate());
        }

        @Override
        public @NotNull Builder<T> setArg2(ByteBuf arg2) {
            super.setArg2(arg2);
            return this;
        }

        @Override
        public @NotNull Builder<T> setArg3(ByteBuf arg3) {
            super.setArg3(arg3);
            return this;
        }

        @Override
        public @NotNull Builder<T> setHeader(String key, String value) {
            super.setHeader(key, value);
            return this;
        }

        @Override
        public @NotNull Builder<T> setHeaders(Map<String, String> headers) {
            super.setHeaders(headers);
            return this;
        }

        @Override
        public @NotNull Builder<T> setBody(T body) {
            super.setBody(body);
            return this;
        }

        @Override
        public @NotNull Builder<T> setTransportHeader(String key, String value) {
            super.setTransportHeader(key, value);
            return this;
        }

        @Override
        public @NotNull Builder<T> setTransportHeaders(@NotNull Map<String, String> transportHeaders) {
            super.setTransportHeaders(transportHeaders);
            return this;
        }

        @Override
        public @NotNull Builder<T> setResponseCode(ResponseCode responseCode) {
            super.setResponseCode(responseCode);
            return this;
        }
    }
}
