<template>
  <div class="sample-list">
    <SearchFrom @search="handleSearch" :search-loading="formSearchLoading"/>
    <AButton class="add-btn" type="primary" @click="handleAddSample">Add Sample</AButton>
    <SampleTable :loading="dataLoading" :pagination="tablePagination" :data-source="sampleList"
                  @change="handleTablePaginationChange" @delete="handleDeleteSample" @edit="handleEditSample"/>
    <SampleModal title="Sample" :visible="modalVisible" :confirm-loading="modalConfirmLoading"
                  @ok="handleModalSubmit" @cancel="handleModalCancel"/>
  </div>
</template>

<script>
import SearchFrom from '@/pages/SampleList/SearchForm'
import SampleTable from '@/pages/SampleList/SampleTable'
import SampleModal from '@/pages/SampleList/SampleModal'

export default {
  components: {
    SampleModal,
    SampleTable,
    SearchFrom,
  },
  methods: {
    async handleSearch(ev) {
      this.$store.commit('setSearchParams', ev)
      this.$store.commit('setFormSearchLoading', true)
      this.$store.commit('setPageIndex', 1)
      await this.$store.dispatch('searchSampleList')
      this.$store.commit('setFormSearchLoading', false)
    },
    async handleModalSubmit(ev) {
      this.$store.commit('setModalData', { confirmLoading: true })
      await this.$store.dispatch('saveSample')
      this.$store.commit('setModalData', {
        visible: false,
        confirmLoading: false,
      })
    },
    handleModalCancel(ev) {
      this.$store.commit('setModalData', {
        visible: false,
        confirmLoading: false,
      })
    },
    handleAddSample(ev) {
      this.$store.commit('setModalData', {
        visible: true,
        confirmLoading: false,
        // data
        id: undefined,
        name: undefined,
        type: undefined,
      })
    },
    async handleTablePaginationChange(ev) {
      this.$store.commit('setPageIndex', ev.current)
      this.$store.commit('setPageSize', ev.pageSize)
      await this.$store.dispatch('searchSampleList')
    },
    async handleDeleteSample(ev) {
      await this.$store.dispatch('deleteSample', ev)
      await this.$store.dispatch('searchSampleList')
    },
    async handleEditSample(ev) {
      this.$store.commit('setModalData', {
        visible: true,
        confirmLoading: false,
        // data
        ...ev
      })
    }
  },
  computed: {
    dataLoading() {
      return this.$store.state.dataLoading
    },
    tablePagination() {
      return {
        total: this.$store.state.total,
        current: this.$store.state.pageIndex,
        pageSize: this.$store.state.pageSize,
        showQuickJumper: true,
        showSizeChanger: true,
        showTotal: (total, range) => `Total: ${total}`,
      }
    },
    sampleList() {
      return this.$store.state.sampleList
    },
    modalVisible() {
      return this.$store.state.modal.visible
    },
    modalConfirmLoading() {
      return this.$store.state.modal.confirmLoading
    },
    formSearchLoading() {
      return this.$store.state.formSearchLoading
    }
  },
  created() {
    this.$store.dispatch('initialLoadPageData')
  },
}
</script>

<style scoped>

.sample-list {
  padding: 30px;
  background-color: #eee;;
}

.add-btn {
  margin: 15px 0;
  height: 40px;
}

</style>
