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

        <a-form-item label="物料编号" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'code', validatorRules.code]" placeholder="请输入物料编号"></a-input>
        </a-form-item>
        <a-form-item label="物料名称" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'name', validatorRules.name]" placeholder="请输入物料名称"></a-input>
        </a-form-item>
        <a-form-item label="物料类型" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <j-dict-select-tag :triggerChange="true"  v-decorator="[ 'type', validatorRules.type]" placeholder="请选择物料类型" 
            dictCode="sys_category,name,code,pid=1254285825811144705"/>
        </a-form-item>
        <a-form-item label="物料单位" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <j-dict-select-tag :triggerChange="true"  v-decorator="[ 'unitId', validatorRules.unitId]" placeholder="请选择物料单位" 
            dictCode="sys_dict_item,item_text,item_value,dict_id=1254414001631567873"/>
        </a-form-item>
        <a-form-item label="安全库存" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'safeNum', validatorRules.safeNum]" placeholder="请输入安全库存" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="有效天数" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'validDay', validatorRules.validDay]" placeholder="请输入有效天" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="备注" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'remark', validatorRules.remark]" placeholder="请输入remark"></a-input>
        </a-form-item>

      </a-form>
    </a-spin>
  </a-modal>
</template>

<script>

  import { httpAction } from '@/api/manage'
  import pick from 'lodash.pick'
  import { validateDuplicateValue } from '@/utils/util'
  import JDate from '@/components/jeecg/JDate'  

  export default {
    name: "MesMaterialModal",
    components: { 
      JDate,
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
          code: {rules: [
          ]},
          name: {rules: [
          ]},
          type: {rules: [
          ]},
          categoryId: {rules: [
          ]},
          unitId: {rules: [
          ]},
          safeNum: {rules: [
          ]},
          validDay: {rules: [
          ]},
          quantity: {rules: [
          ]},
          isDel: {rules: [
          ]},
          remark: {rules: [
          ]},
          createBy: {rules: [
          ]},
          updateBy: {rules: [
          ]},
          createTime: {rules: [
          ]},
          updateTime: {rules: [
          ]},
        },
        url: {
          add: "/org.jeecg.modules.mes/mesMaterial/add",
          edit: "/org.jeecg.modules.mes/mesMaterial/edit",
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
          this.form.setFieldsValue(pick(this.model,'code','name','type','categoryId','unitId','safeNum','validDay','quantity','isDel','remark','createBy','updateBy','createTime','updateTime'))
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
        this.form.setFieldsValue(pick(row,'code','name','type','categoryId','unitId','safeNum','validDay','quantity','isDel','remark','createBy','updateBy','createTime','updateTime'))
      },

      
    }
  }
</script>