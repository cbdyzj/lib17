<template>
  <div class="sample-table">
    <ATable @change="handlePaginationChange" :loading="loading" :columns="columns" :data-source="dataSource"
             :pagination="pagination">
      <template #name="text"><a>{{ text }}</a></template>
      <template #type="text"><span>{{ titleCase(text) }}</span></template>
      <template #custom-title><span><AIcon type="smile-o"/> Name</span></template>
      <template #action="record">
        <span>
          <a @click="handleEdit(record)">Edit</a>
          <ADivider type="vertical"/>
          <APopconfirm placement="top" ok-text="Yes" cancel-text="No" @confirm="handleDelete(record)">
            <template #title>
              <p>Are you sure you want to delete?</p>
            </template>
            <a>Delete</a>
          </APopconfirm>
        </span>
      </template>
    </ATable>
  </div>
</template>
<script>

export default {
  props: {
    loading: {
      type: Boolean,
      default() {
        return false
      }
    },
    pagination: {
      type: Object,
      default() {
        return {}
      }
    },
    dataSource: {
      type: Array,
      default() {
        return []
      }
    }
  },
  computed: {
    columns() {
      return [
        {
          title: 'ID',
          dataIndex: 'id',
          key: 'id',
        },
        {
          dataIndex: 'name',
          key: 'name',
          slots: { title: 'custom-title' },
          scopedSlots: { customRender: 'name' },
        },
        {
          title: 'Type',
          key: 'type',
          dataIndex: 'type',
          scopedSlots: { customRender: 'type' },
        },
        {
          title: 'Action',
          key: 'action',
          scopedSlots: { customRender: 'action' },
        },
      ]
    }
  },
  methods: {
    handleEdit(ev) {
      this.$emit('edit', ev)
    },
    handleDelete(ev) {
      this.$emit('delete', ev.id)
    },
    handlePaginationChange(ev) {
      this.$emit('change', ev)
    },
    titleCase(s) {
      s = String(s)
      return s.slice(0, 1).toUpperCase() + s.slice(1)
    }
  }
};
</script>

<style scoped>
.sample-table {
  padding: 40px;
  background-color: #fff;
  border-radius: 10px;
}
</style>