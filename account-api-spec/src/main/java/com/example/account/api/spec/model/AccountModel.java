// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: AccountModel.proto

package com.example.account.api.spec.model;

public final class AccountModel {
  private AccountModel() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface AccountModelProtoOrBuilder extends
      // @@protoc_insertion_point(interface_extends:com.example.account.api.spec.model.AccountModelProto)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>int64 id = 1;</code>
     */
    long getId();

    /**
     * <code>string name = 2;</code>
     */
    java.lang.String getName();
    /**
     * <code>string name = 2;</code>
     */
    com.google.protobuf.ByteString
        getNameBytes();

    /**
     * <code>string email = 3;</code>
     */
    java.lang.String getEmail();
    /**
     * <code>string email = 3;</code>
     */
    com.google.protobuf.ByteString
        getEmailBytes();

    /**
     * <code>string password = 4;</code>
     */
    java.lang.String getPassword();
    /**
     * <code>string password = 4;</code>
     */
    com.google.protobuf.ByteString
        getPasswordBytes();

    /**
     * <code>bool active = 5;</code>
     */
    boolean getActive();
  }
  /**
   * Protobuf type {@code com.example.account.api.spec.model.AccountModelProto}
   */
  public  static final class AccountModelProto extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:com.example.account.api.spec.model.AccountModelProto)
      AccountModelProtoOrBuilder {
  private static final long serialVersionUID = 0L;
    // Use AccountModelProto.newBuilder() to construct.
    private AccountModelProto(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private AccountModelProto() {
      id_ = 0L;
      name_ = "";
      email_ = "";
      password_ = "";
      active_ = false;
    }

    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return this.unknownFields;
    }
    private AccountModelProto(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      this();
      if (extensionRegistry == null) {
        throw new java.lang.NullPointerException();
      }
      int mutable_bitField0_ = 0;
      com.google.protobuf.UnknownFieldSet.Builder unknownFields =
          com.google.protobuf.UnknownFieldSet.newBuilder();
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            case 8: {

              id_ = input.readInt64();
              break;
            }
            case 18: {
              java.lang.String s = input.readStringRequireUtf8();

              name_ = s;
              break;
            }
            case 26: {
              java.lang.String s = input.readStringRequireUtf8();

              email_ = s;
              break;
            }
            case 34: {
              java.lang.String s = input.readStringRequireUtf8();

              password_ = s;
              break;
            }
            case 40: {

              active_ = input.readBool();
              break;
            }
            default: {
              if (!parseUnknownFieldProto3(
                  input, unknownFields, extensionRegistry, tag)) {
                done = true;
              }
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e).setUnfinishedMessage(this);
      } finally {
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.example.account.api.spec.model.AccountModel.internal_static_com_example_account_api_spec_model_AccountModelProto_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.example.account.api.spec.model.AccountModel.internal_static_com_example_account_api_spec_model_AccountModelProto_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.example.account.api.spec.model.AccountModel.AccountModelProto.class, com.example.account.api.spec.model.AccountModel.AccountModelProto.Builder.class);
    }

    public static final int ID_FIELD_NUMBER = 1;
    private long id_;
    /**
     * <code>int64 id = 1;</code>
     */
    public long getId() {
      return id_;
    }

    public static final int NAME_FIELD_NUMBER = 2;
    private volatile java.lang.Object name_;
    /**
     * <code>string name = 2;</code>
     */
    public java.lang.String getName() {
      java.lang.Object ref = name_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        name_ = s;
        return s;
      }
    }
    /**
     * <code>string name = 2;</code>
     */
    public com.google.protobuf.ByteString
        getNameBytes() {
      java.lang.Object ref = name_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        name_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int EMAIL_FIELD_NUMBER = 3;
    private volatile java.lang.Object email_;
    /**
     * <code>string email = 3;</code>
     */
    public java.lang.String getEmail() {
      java.lang.Object ref = email_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        email_ = s;
        return s;
      }
    }
    /**
     * <code>string email = 3;</code>
     */
    public com.google.protobuf.ByteString
        getEmailBytes() {
      java.lang.Object ref = email_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        email_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int PASSWORD_FIELD_NUMBER = 4;
    private volatile java.lang.Object password_;
    /**
     * <code>string password = 4;</code>
     */
    public java.lang.String getPassword() {
      java.lang.Object ref = password_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        password_ = s;
        return s;
      }
    }
    /**
     * <code>string password = 4;</code>
     */
    public com.google.protobuf.ByteString
        getPasswordBytes() {
      java.lang.Object ref = password_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        password_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int ACTIVE_FIELD_NUMBER = 5;
    private boolean active_;
    /**
     * <code>bool active = 5;</code>
     */
    public boolean getActive() {
      return active_;
    }

    private byte memoizedIsInitialized = -1;
    @java.lang.Override
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      memoizedIsInitialized = 1;
      return true;
    }

    @java.lang.Override
    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      if (id_ != 0L) {
        output.writeInt64(1, id_);
      }
      if (!getNameBytes().isEmpty()) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 2, name_);
      }
      if (!getEmailBytes().isEmpty()) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 3, email_);
      }
      if (!getPasswordBytes().isEmpty()) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 4, password_);
      }
      if (active_ != false) {
        output.writeBool(5, active_);
      }
      unknownFields.writeTo(output);
    }

    @java.lang.Override
    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (id_ != 0L) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt64Size(1, id_);
      }
      if (!getNameBytes().isEmpty()) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, name_);
      }
      if (!getEmailBytes().isEmpty()) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(3, email_);
      }
      if (!getPasswordBytes().isEmpty()) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(4, password_);
      }
      if (active_ != false) {
        size += com.google.protobuf.CodedOutputStream
          .computeBoolSize(5, active_);
      }
      size += unknownFields.getSerializedSize();
      memoizedSize = size;
      return size;
    }

    @java.lang.Override
    public boolean equals(final java.lang.Object obj) {
      if (obj == this) {
       return true;
      }
      if (!(obj instanceof com.example.account.api.spec.model.AccountModel.AccountModelProto)) {
        return super.equals(obj);
      }
      com.example.account.api.spec.model.AccountModel.AccountModelProto other = (com.example.account.api.spec.model.AccountModel.AccountModelProto) obj;

      boolean result = true;
      result = result && (getId()
          == other.getId());
      result = result && getName()
          .equals(other.getName());
      result = result && getEmail()
          .equals(other.getEmail());
      result = result && getPassword()
          .equals(other.getPassword());
      result = result && (getActive()
          == other.getActive());
      result = result && unknownFields.equals(other.unknownFields);
      return result;
    }

    @java.lang.Override
    public int hashCode() {
      if (memoizedHashCode != 0) {
        return memoizedHashCode;
      }
      int hash = 41;
      hash = (19 * hash) + getDescriptor().hashCode();
      hash = (37 * hash) + ID_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
          getId());
      hash = (37 * hash) + NAME_FIELD_NUMBER;
      hash = (53 * hash) + getName().hashCode();
      hash = (37 * hash) + EMAIL_FIELD_NUMBER;
      hash = (53 * hash) + getEmail().hashCode();
      hash = (37 * hash) + PASSWORD_FIELD_NUMBER;
      hash = (53 * hash) + getPassword().hashCode();
      hash = (37 * hash) + ACTIVE_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashBoolean(
          getActive());
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static com.example.account.api.spec.model.AccountModel.AccountModelProto parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.example.account.api.spec.model.AccountModel.AccountModelProto parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.example.account.api.spec.model.AccountModel.AccountModelProto parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.example.account.api.spec.model.AccountModel.AccountModelProto parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.example.account.api.spec.model.AccountModel.AccountModelProto parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.example.account.api.spec.model.AccountModel.AccountModelProto parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.example.account.api.spec.model.AccountModel.AccountModelProto parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.example.account.api.spec.model.AccountModel.AccountModelProto parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.example.account.api.spec.model.AccountModel.AccountModelProto parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static com.example.account.api.spec.model.AccountModel.AccountModelProto parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.example.account.api.spec.model.AccountModel.AccountModelProto parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.example.account.api.spec.model.AccountModel.AccountModelProto parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }

    @java.lang.Override
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    public static Builder newBuilder(com.example.account.api.spec.model.AccountModel.AccountModelProto prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
    }
    @java.lang.Override
    public Builder toBuilder() {
      return this == DEFAULT_INSTANCE
          ? new Builder() : new Builder().mergeFrom(this);
    }

    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * Protobuf type {@code com.example.account.api.spec.model.AccountModelProto}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:com.example.account.api.spec.model.AccountModelProto)
        com.example.account.api.spec.model.AccountModel.AccountModelProtoOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.example.account.api.spec.model.AccountModel.internal_static_com_example_account_api_spec_model_AccountModelProto_descriptor;
      }

      @java.lang.Override
      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.example.account.api.spec.model.AccountModel.internal_static_com_example_account_api_spec_model_AccountModelProto_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.example.account.api.spec.model.AccountModel.AccountModelProto.class, com.example.account.api.spec.model.AccountModel.AccountModelProto.Builder.class);
      }

      // Construct using com.example.account.api.spec.model.AccountModel.AccountModelProto.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessageV3
                .alwaysUseFieldBuilders) {
        }
      }
      @java.lang.Override
      public Builder clear() {
        super.clear();
        id_ = 0L;

        name_ = "";

        email_ = "";

        password_ = "";

        active_ = false;

        return this;
      }

      @java.lang.Override
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.example.account.api.spec.model.AccountModel.internal_static_com_example_account_api_spec_model_AccountModelProto_descriptor;
      }

      @java.lang.Override
      public com.example.account.api.spec.model.AccountModel.AccountModelProto getDefaultInstanceForType() {
        return com.example.account.api.spec.model.AccountModel.AccountModelProto.getDefaultInstance();
      }

      @java.lang.Override
      public com.example.account.api.spec.model.AccountModel.AccountModelProto build() {
        com.example.account.api.spec.model.AccountModel.AccountModelProto result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      @java.lang.Override
      public com.example.account.api.spec.model.AccountModel.AccountModelProto buildPartial() {
        com.example.account.api.spec.model.AccountModel.AccountModelProto result = new com.example.account.api.spec.model.AccountModel.AccountModelProto(this);
        result.id_ = id_;
        result.name_ = name_;
        result.email_ = email_;
        result.password_ = password_;
        result.active_ = active_;
        onBuilt();
        return result;
      }

      @java.lang.Override
      public Builder clone() {
        return (Builder) super.clone();
      }
      @java.lang.Override
      public Builder setField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          java.lang.Object value) {
        return (Builder) super.setField(field, value);
      }
      @java.lang.Override
      public Builder clearField(
          com.google.protobuf.Descriptors.FieldDescriptor field) {
        return (Builder) super.clearField(field);
      }
      @java.lang.Override
      public Builder clearOneof(
          com.google.protobuf.Descriptors.OneofDescriptor oneof) {
        return (Builder) super.clearOneof(oneof);
      }
      @java.lang.Override
      public Builder setRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          int index, java.lang.Object value) {
        return (Builder) super.setRepeatedField(field, index, value);
      }
      @java.lang.Override
      public Builder addRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          java.lang.Object value) {
        return (Builder) super.addRepeatedField(field, value);
      }
      @java.lang.Override
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.example.account.api.spec.model.AccountModel.AccountModelProto) {
          return mergeFrom((com.example.account.api.spec.model.AccountModel.AccountModelProto)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.example.account.api.spec.model.AccountModel.AccountModelProto other) {
        if (other == com.example.account.api.spec.model.AccountModel.AccountModelProto.getDefaultInstance()) return this;
        if (other.getId() != 0L) {
          setId(other.getId());
        }
        if (!other.getName().isEmpty()) {
          name_ = other.name_;
          onChanged();
        }
        if (!other.getEmail().isEmpty()) {
          email_ = other.email_;
          onChanged();
        }
        if (!other.getPassword().isEmpty()) {
          password_ = other.password_;
          onChanged();
        }
        if (other.getActive() != false) {
          setActive(other.getActive());
        }
        this.mergeUnknownFields(other.unknownFields);
        onChanged();
        return this;
      }

      @java.lang.Override
      public final boolean isInitialized() {
        return true;
      }

      @java.lang.Override
      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        com.example.account.api.spec.model.AccountModel.AccountModelProto parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.example.account.api.spec.model.AccountModel.AccountModelProto) e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }

      private long id_ ;
      /**
       * <code>int64 id = 1;</code>
       */
      public long getId() {
        return id_;
      }
      /**
       * <code>int64 id = 1;</code>
       */
      public Builder setId(long value) {
        
        id_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>int64 id = 1;</code>
       */
      public Builder clearId() {
        
        id_ = 0L;
        onChanged();
        return this;
      }

      private java.lang.Object name_ = "";
      /**
       * <code>string name = 2;</code>
       */
      public java.lang.String getName() {
        java.lang.Object ref = name_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          name_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>string name = 2;</code>
       */
      public com.google.protobuf.ByteString
          getNameBytes() {
        java.lang.Object ref = name_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          name_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>string name = 2;</code>
       */
      public Builder setName(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  
        name_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>string name = 2;</code>
       */
      public Builder clearName() {
        
        name_ = getDefaultInstance().getName();
        onChanged();
        return this;
      }
      /**
       * <code>string name = 2;</code>
       */
      public Builder setNameBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
        
        name_ = value;
        onChanged();
        return this;
      }

      private java.lang.Object email_ = "";
      /**
       * <code>string email = 3;</code>
       */
      public java.lang.String getEmail() {
        java.lang.Object ref = email_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          email_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>string email = 3;</code>
       */
      public com.google.protobuf.ByteString
          getEmailBytes() {
        java.lang.Object ref = email_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          email_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>string email = 3;</code>
       */
      public Builder setEmail(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  
        email_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>string email = 3;</code>
       */
      public Builder clearEmail() {
        
        email_ = getDefaultInstance().getEmail();
        onChanged();
        return this;
      }
      /**
       * <code>string email = 3;</code>
       */
      public Builder setEmailBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
        
        email_ = value;
        onChanged();
        return this;
      }

      private java.lang.Object password_ = "";
      /**
       * <code>string password = 4;</code>
       */
      public java.lang.String getPassword() {
        java.lang.Object ref = password_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          password_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>string password = 4;</code>
       */
      public com.google.protobuf.ByteString
          getPasswordBytes() {
        java.lang.Object ref = password_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          password_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>string password = 4;</code>
       */
      public Builder setPassword(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  
        password_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>string password = 4;</code>
       */
      public Builder clearPassword() {
        
        password_ = getDefaultInstance().getPassword();
        onChanged();
        return this;
      }
      /**
       * <code>string password = 4;</code>
       */
      public Builder setPasswordBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
        
        password_ = value;
        onChanged();
        return this;
      }

      private boolean active_ ;
      /**
       * <code>bool active = 5;</code>
       */
      public boolean getActive() {
        return active_;
      }
      /**
       * <code>bool active = 5;</code>
       */
      public Builder setActive(boolean value) {
        
        active_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>bool active = 5;</code>
       */
      public Builder clearActive() {
        
        active_ = false;
        onChanged();
        return this;
      }
      @java.lang.Override
      public final Builder setUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.setUnknownFieldsProto3(unknownFields);
      }

      @java.lang.Override
      public final Builder mergeUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.mergeUnknownFields(unknownFields);
      }


      // @@protoc_insertion_point(builder_scope:com.example.account.api.spec.model.AccountModelProto)
    }

    // @@protoc_insertion_point(class_scope:com.example.account.api.spec.model.AccountModelProto)
    private static final com.example.account.api.spec.model.AccountModel.AccountModelProto DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new com.example.account.api.spec.model.AccountModel.AccountModelProto();
    }

    public static com.example.account.api.spec.model.AccountModel.AccountModelProto getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<AccountModelProto>
        PARSER = new com.google.protobuf.AbstractParser<AccountModelProto>() {
      @java.lang.Override
      public AccountModelProto parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new AccountModelProto(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<AccountModelProto> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<AccountModelProto> getParserForType() {
      return PARSER;
    }

    @java.lang.Override
    public com.example.account.api.spec.model.AccountModel.AccountModelProto getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_example_account_api_spec_model_AccountModelProto_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_example_account_api_spec_model_AccountModelProto_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\022AccountModel.proto\022\"com.example.accoun" +
      "t.api.spec.model\"^\n\021AccountModelProto\022\n\n" +
      "\002id\030\001 \001(\003\022\014\n\004name\030\002 \001(\t\022\r\n\005email\030\003 \001(\t\022\020" +
      "\n\010password\030\004 \001(\t\022\016\n\006active\030\005 \001(\010b\006proto3"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
    internal_static_com_example_account_api_spec_model_AccountModelProto_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_com_example_account_api_spec_model_AccountModelProto_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_example_account_api_spec_model_AccountModelProto_descriptor,
        new java.lang.String[] { "Id", "Name", "Email", "Password", "Active", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
