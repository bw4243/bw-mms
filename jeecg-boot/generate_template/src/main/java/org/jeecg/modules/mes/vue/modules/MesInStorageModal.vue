<template>
  <a-modal
    :title="title"
    :width="width"
    :visible="visible"
    :confirmLoading="confirmLoading"
    @ok="handleOk"
    @cancel="handleCancel"
    cancelText="关闭">
    <a-spin :spinning="confirmLoading">
      <a-form :form="form">

        <a-form-item label="入库或出库单号" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <j-dict-select-tag type="radio" v-decorator="['number', validatorRules.number]" :trigger-change="true" dictCode="" placeholder="请选择入库或出库单号"/>
        </a-form-item>
        <a-form-item label="订单号" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'orderNumber', validatorRules.orderNumber]" placeholder="请输入订单号"></a-input>
        </a-form-item>
        <a-form-item label="入库单类别" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <j-dict-select-tag type="list" v-decorator="['cateId', validatorRules.cateId]" :trigger-change="true" dictCode="srote_in_order_type" placeholder="请选择入库单类别"/>
        </a-form-item>
        <a-form-item label="公司ID" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <j-dict-select-tag type="list" v-decorator="['companyId', validatorRules.companyId]" :trigger-change="true" dictCode="mes_company,name,id" placeholder="请选择公司ID"/>
        </a-form-item>
        <a-form-item label="状态：
1：初始提交；
2：审核中；
3：审核通过；
4：审核未通过；
5：已完成；" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <j-dict-select-tag type="list" v-decorator="['status', validatorRules.status]" :trigger-change="true" dictCode="" placeholder="请选择状态：
1：初始提交；
2：审核中；
3：审核通过；
4：审核未通过；
5：已完成；"/>
        </a-form-item>
        <a-form-item label="remark" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-textarea v-decorator="['remark', validatorRules.remark]" rows="4" placeholder="请输入remark"/>
        </a-form-item>

      </a-form>
    </a-spin>
  </a-modal>
</template>

<script>

  import { httpAction } from '@/api/manage'
  import pick from 'lodash.pick'
  import { validateDuplicateValue } from '@/utils/util'
  import JDictSelectTag from "@/components/dict/JDictSelectTag"

  export default {
    name: "MesInStorageModal",
    components: { 
      JDictSelectTag,
    },
    data () {
      return {
        form: this.$form.createForm(this),
        title:"操作",
        width:800,
        visible: false,
        model: {},
        labelCol: {
          xs: { span: 24 },
          sm: { span: 5 },
        },
        wrapperCol: {
          xs: { span: 24 },
          sm: { span: 16 },
        },
        confirmLoading: false,
        validatorRules: {
          number: {rules: [
            {required: true, message: '请输入入库或出库单号!'},
          ]},
          orderNumber: {rules: [
            {required: true, message: '请输入订单号!'},
          ]},
          cateId: {rules: [
            {required: true, message: '请输入入库单类别!'},
          ]},
          companyId: {rules: [
            {required: true, message: '请输入公司ID!'},
          ]},
          status: {rules: [
            {required: true, message: '请输入状态：
1：初始提交；
2：审核中；
3：审核通过；
4：审核未通过；
5：已完成；!'},
          ]},
          remark: {rules: [
            {required: true, message: '请输入remark!'},
          ]},
        },
        url: {
          add: "/mes/mesInStorage/add",
          edit: "/mes/mesInStorage/edit",
        }
      }
    },
    created () {
    },
    methods: {
      add () {
        this.edit({});
      },
      edit (record) {
        this.form.resetFields();
        this.model = Object.assign({}, record);
        this.visible = true;
        this.$nextTick(() => {
          this.form.setFieldsValue(pick(this.model,'number','orderNumber','cateId','companyId','status','remark','rejectRemark'))
        })
      },
      close () {
        this.$emit('close');
        this.visible = false;
      },
      handleOk () {
        const that = this;
        // 触发表单验证
        this.form.validateFields((err, values) => {
          if (!err) {
            that.confirmLoading = true;
            let httpurl = '';
            let method = '';
            if(!this.model.id){
              httpurl+=this.url.add;
              method = 'post';
            }else{
              httpurl+=this.url.edit;
               method = 'put';
            }
            let formData = Object.assign(this.model, values);
            console.log("表单提交数据",formData)
            httpAction(httpurl,formData,method).then((res)=>{
              if(res.success){
                that.$message.success(res.message);
                that.$emit('ok');
              }else{
                that.$message.warning(res.message);
              }
            }).finally(() => {
              that.confirmLoading = false;
              that.close();
            })
          }
         
        })
      },
      handleCancel () {
        this.close()
      },
      popupCallback(row){
        this.form.setFieldsValue(pick(row,'number','orderNumber','cateId','companyId','status','remark','rejectRemark'))
      },

      
    }
  }
</script>