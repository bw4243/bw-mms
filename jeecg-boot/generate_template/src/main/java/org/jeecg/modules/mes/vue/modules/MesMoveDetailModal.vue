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

        <a-form-item label="移动单编号" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'number', validatorRules.number]" placeholder="请输入移动单编号"></a-input>
        </a-form-item>
        <a-form-item label="物料ID" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <j-dict-select-tag type="list" v-decorator="['materialId', validatorRules.materialId]" :trigger-change="true" dictCode="mes_material,name,id" placeholder="请选择物料ID"/>
        </a-form-item>
        <a-form-item label="companyId" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <j-dict-select-tag type="list" v-decorator="['companyId', validatorRules.companyId]" :trigger-change="true" dictCode="mes_company,name,id" placeholder="请选择companyId"/>
        </a-form-item>
        <a-form-item label="planQuantity" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'planQuantity', validatorRules.planQuantity]" placeholder="请输入planQuantity" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="quantity" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'quantity', validatorRules.quantity]" placeholder="请输入quantity" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="状态：
1：初始提交；
2：审核中；
3：审核通过；
4：审核未通过；
5：已完成；" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <j-multi-select-tag type="list_multi" v-decorator="['status', validatorRules.status]" :trigger-change="true" dictCode="storage_in_order_status" placeholder="请选择状态：
1：初始提交；
2：审核中；
3：审核通过；
4：审核未通过；
5：已完成；"/>
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
  import JMultiSelectTag from "@/components/dict/JMultiSelectTag"

  export default {
    name: "MesMoveDetailModal",
    components: { 
      JDictSelectTag,
      JMultiSelectTag,
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
          ]},
          materialId: {rules: [
          ]},
          companyId: {rules: [
          ]},
          planQuantity: {rules: [
          ]},
          quantity: {rules: [
          ]},
          status: {rules: [
          ]},
        },
        url: {
          add: "/mes/mesMoveDetail/add",
          edit: "/mes/mesMoveDetail/edit",
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
          this.form.setFieldsValue(pick(this.model,'number','materialId','companyId','planQuantity','quantity','status'))
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
        this.form.setFieldsValue(pick(row,'number','materialId','companyId','planQuantity','quantity','status'))
      },

      
    }
  }
</script>