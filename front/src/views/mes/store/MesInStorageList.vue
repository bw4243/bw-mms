<template>
  <a-card :bordered="false">
    <!-- 查询区域 -->
    <div class="table-page-search-wrapper">
      <a-form layout="inline" @keyup.enter.native="searchQuery">
        <a-row :gutter="24">
          <a-col :xl="10" :lg="11" :md="12" :sm="24">
            <a-form-item label="入库单类别">
              <a-input placeholder="请输入最小值" class="query-group-cust" v-model="queryParam.cateId_begin"></a-input>
              <span class="query-group-split-cust"></span>
              <a-input placeholder="请输入最大值" class="query-group-cust" v-model="queryParam.cateId_end"></a-input>
            </a-form-item>
          </a-col>
          <a-col :xl="10" :lg="11" :md="12" :sm="24">
            <a-form-item label="公司ID">
              <a-input placeholder="请输入最小值" class="query-group-cust" v-model="queryParam.companyId_begin"></a-input>
              <span class="query-group-split-cust"></span>
              <a-input placeholder="请输入最大值" class="query-group-cust" v-model="queryParam.companyId_end"></a-input>
            </a-form-item>
          </a-col>
          <a-col :xl="6" :lg="7" :md="8" :sm="24">
            <span style="float: left;overflow: hidden;" class="table-page-search-submitButtons">
              <a-button type="primary" @click="searchQuery" icon="search">查询</a-button>
              <a-button type="primary" @click="searchReset" icon="reload" style="margin-left: 8px">重置</a-button>
              <a @click="handleToggleSearch" style="margin-left: 8px">
                {{ toggleSearchStatus ? '收起' : '展开' }}
                <a-icon :type="toggleSearchStatus ? 'up' : 'down'"/>
              </a>
            </span>
          </a-col>

        </a-row>
      </a-form>
    </div>
    <!-- 查询区域-END -->
    
    <!-- 操作按钮区域 -->
    <div class="table-operator">
      <a-button @click="handleAdd" type="primary" icon="plus">新增</a-button>
      <a-button type="primary" icon="download" @click="handleExportXls('mes_in_storage')">导出</a-button>
      <a-upload name="file" :showUploadList="false" :multiple="false" :headers="tokenHeader" :action="importExcelUrl" @change="handleImportExcel">
        <a-button type="primary" icon="import">导入</a-button>
      </a-upload>
      <a-dropdown v-if="selectedRowKeys.length > 0">
        <a-menu slot="overlay">
          <a-menu-item key="1" @click="batchDel"><a-icon type="delete"/>删除</a-menu-item>
        </a-menu>
        <a-button style="margin-left: 8px"> 批量操作 <a-icon type="down" /></a-button>
      </a-dropdown>
    </div>

    <!-- table区域-begin -->
    <div>
      <div class="ant-alert ant-alert-info" style="margin-bottom: 16px;">
        <i class="anticon anticon-info-circle ant-alert-icon"></i> 已选择 <a style="font-weight: 600">{{ selectedRowKeys.length }}</a>项
        <a style="margin-left: 24px" @click="onClearSelected">清空</a>
      </div>

      <a-table
        ref="table"
        size="middle"
        bordered
        rowKey="id"
        :columns="columns"
        :dataSource="dataSource"
        :pagination="ipagination"
        :loading="loading"
        :rowSelection="{fixed:true,selectedRowKeys: selectedRowKeys, onChange: onSelectChange}"
        
        @change="handleTableChange">

        <template slot="htmlSlot" slot-scope="text">
          <div v-html="text"></div>
        </template>
        <template slot="imgSlot" slot-scope="text">
          <span v-if="!text" style="font-size: 12px;font-style: italic;">无此图片</span>
          <img v-else :src="getImgView(text)" height="25px" alt="图片不存在" style="max-width:80px;font-size: 12px;font-style: italic;"/>
        </template>
        <template slot="fileSlot" slot-scope="text">
          <span v-if="!text" style="font-size: 12px;font-style: italic;">无此文件</span>
          <a-button
            v-else
            :ghost="true"
            type="primary"
            icon="download"
            size="small"
            @click="uploadFile(text)">
            下载
          </a-button>
        </template>
        <span slot="detail" slot-scope="text, record">
          <a-badge count="5">
            <a-button  icon="unordered-list" @click="handleDetailEdit(text)" >明细</a-button>
          </a-badge>
        </span>
        <span slot="action" slot-scope="text, record">
          <a @click="handleEdit(record)">编辑</a>

          <a-divider type="vertical" />
          <a-dropdown>
            <a class="ant-dropdown-link">更多 <a-icon type="down" /></a>
            <a-menu slot="overlay">
              <a-menu-item>
                <a-popconfirm title="确定删除吗?" @confirm="() => handleDelete(record.id)">
                  <a>删除</a>
                </a-popconfirm>
              </a-menu-item>
            </a-menu>
          </a-dropdown>
        </span>

      </a-table>
    </div>

    <mesInStorage-modal ref="modalForm" @ok="modalFormOk"></mesInStorage-modal>
    <MesInStorageDetailModal ref="detailModalForm" @ok="modalFormOk"></MesInStorageDetailModal>

  </a-card>
</template>

<script>

  import { JeecgListMixin } from '@/mixins/JeecgListMixin'
  import MesInStorageModal from './modules/MesInStorageModal'
  import MesInStorageDetailModal from './modules/MesInStorageDetailModal'
  import {filterMultiDictText} from '@/components/dict/JDictSelectUtil'

  export default {
    name: "MesInStorageList",
    mixins:[JeecgListMixin],
    components: {
      MesInStorageModal,
      MesInStorageDetailModal,
    },
    data () {
      return {
        description: 'mes_in_storage管理页面',
        visible: false,
        // 表头
        columns: [
          {
            title: '#',
            dataIndex: '',
            key:'rowIndex',
            width:60,
            align:"center",
            customRender:function (t,r,index) {
              return parseInt(index)+1;
            },
          },
          {
            title:'入库单号',
            align:"center",
            dataIndex: 'number'
          },
          {
            title:'订单号',
            align:"center",
            dataIndex: 'orderNumber'
          },
          {
            title:'供货商',
            align:"center",
            dataIndex: 'companyId_dictText'
          },
          {
            title:'状态',
            align:"center",
            dataIndex: 'status_dictText'
          },
          {
            title:'入库单明细',
            align:"center",
            scopedSlots: { customRender: 'detail' }
          },
          {
            title: '操作',
            dataIndex: 'action',
            align:"center",
            scopedSlots: { customRender: 'action' }
          }
        ],
        url: {
          list: "/mes/mesInStorage/list",
          delete: "/mes/mesInStorage/delete",
          deleteBatch: "/mes/mesInStorage/deleteBatch",
          exportXlsUrl: "/mes/mesInStorage/exportXls",
          importExcelUrl: "mes/mesInStorage/importExcel",
        },
        dictOptions:{},
      }
    },
    computed: {
      importExcelUrl: function(){
        return `${window._CONFIG['domianURL']}/${this.url.importExcelUrl}`;
      }
    },
    methods: {
      initDictConfig(){
      },
      handleDetailEdit: function (text) {
        // this.$refs.detailModalForm.edit(text);
         this.$refs.detailModalForm.title = "入库单单号：" + text.number;
        // this.$refs.detailModalForm.disableSubmit = false;
        console.log(1)
        // this.$refs.detailModalForm.visible= true;
        this.$refs.detailModalForm.loadData(1);
        this.$refs.detailModalForm.visible= true;
        // console.log("1");
       
      },
      showModal() {
        this.$refs.detailModalForm.handleAdd()
      },
      
    }
  }
</script>
<style scoped>
  @import '~@assets/less/common.less';
</style>